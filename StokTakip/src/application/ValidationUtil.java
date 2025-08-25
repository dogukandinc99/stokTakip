package application;

import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.UnaryOperator;

public final class ValidationUtil {

	// --- Pseudo-class for error highlighting (.text-field:error) ---
	private static final PseudoClass ERROR_CLASS = PseudoClass.getPseudoClass("error");

	private ValidationUtil() {
		/* util */ }

	/*
	 * -----------------------------------------------------------------------------
	 * ------------------------------------ Spinner<Double> - Ondalıklı giriş
	 * filtresi Özellikler: - '.' yazılırsa ekranda ',' gösterir (TR alışkanlığı) -
	 * Ölçek (scale) kadar ondalık basamak sınırı - Yapıştırma dahil tüm girişlere
	 * filtre - Odak kaybında / Enter'da parse + min/max clamp + normalize
	 * -----------------------------------------------------------------------------
	 * ------------------------------------
	 */
	public static void applyDecimalSpinner(Spinner<Double> spinner, int scale, Integer maxLen, boolean allowNegative) {

		// 0) Var olan VF'yi kullan; yoksa makul bir tane kur
		if (spinner.getValueFactory() == null) {
			spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(
					allowNegative ? -Double.MAX_VALUE : 0.0, Double.MAX_VALUE, 1.0, 1.0 // step
			));
		}
		SpinnerValueFactory<Double> vf = spinner.getValueFactory();
		spinner.setEditable(true);
		TextField editor = spinner.getEditor();

		StringBuilder pat = new StringBuilder("0");
		if (scale > 0) {
			pat.append('.');
			for (int i = 0; i < scale; i++)
				pat.append('#'); // en fazla 'scale' basamak
		}

		DecimalFormat fmt = new DecimalFormat(pat.toString(), new DecimalFormatSymbols(java.util.Locale.US));
		fmt.setMaximumFractionDigits(scale);
		fmt.setMinimumFractionDigits(0);
		fmt.setGroupingUsed(false);

		StringConverter<Double> conv = new StringConverter<>() {
			@Override
			public String toString(Double val) {
				return (val == null) ? "" : fmt.format(val); // ekranda '.' ile göster
			}

			@Override
			public Double fromString(String s) {
				if (s == null || s.isBlank() || s.equals("-"))
					return 0.0;
				s = s.trim().replace(',', '.'); // parse için '.' kullan
				if (s.equals("."))
					s = "0."; // ".5" gibi durumlar için
				if (allowNegative && s.startsWith("-."))
					s = s.replaceFirst("^\\-\\.", "-0.");
				return Double.parseDouble(s);
			}
		};
		vf.setConverter(conv); // varsa önceki converter'ı bu davranışla değiştir

		// 2) Filtre: [(-)?] + rakamlar + tek NOKTA; ',' otomatik '.' olur
		UnaryOperator<TextFormatter.Change> filter = ch -> {
			String oldText = ch.getControlText();
			String inserted = ch.getText();

			// Virgül -> nokta
			if (inserted != null && inserted.indexOf(',') >= 0) {
				inserted = inserted.replace(',', '.');
				ch.setText(inserted);
			}

			// Başta sadece "." girilirse "0." yap
			if (oldText.isEmpty() && ".".equals(inserted)) {
				ch.setText("0.");
				int s = ch.getRangeStart();
				ch.setCaretPosition(s + 2);
				ch.setAnchor(s + 2);
			}

			// Negatifte "-." -> "-0."
			if (allowNegative && "-".equals(oldText) && ".".equals(inserted)) {
				ch.setText("0.");
				int s = ch.getRangeStart();
				ch.setCaretPosition(s + 2);
				ch.setAnchor(s + 2);
			}

			String next = ch.getControlNewText();

			// Toplam uzunluk sınırı ('.' ve '-' dahil)
			if (maxLen != null && next.length() > maxLen)
				return null;

			// Geçici durumlar: boş ya da tek '-' (negatif serbestse)
			if (next.isEmpty())
				return ch;
			if (allowNegative && next.equals("-"))
				return ch;

			// Desen: [-]?\d*(\.\d*)?
			String sign = allowNegative ? "-?" : "";
			if (!next.matches(sign + "\\d*(\\.\\d*)?"))
				return null;

			// Ölçek: noktadan sonra en fazla 'scale'
			if (scale >= 0) {
				int idx = next.indexOf('.');
				if (idx >= 0) {
					int frac = next.length() - idx - 1;
					if (frac > scale)
						return null;
				}
			}
			return ch;
		};

		// 3) TextFormatter kurulumu
		TextFormatter<Double> tf = new TextFormatter<>(conv, vf.getValue(), filter);
		editor.setTextFormatter(tf);

		// 4) Değer <-> metin senkron
		vf.valueProperty().bindBidirectional(tf.valueProperty());

		// 5) Odak kaybı / Enter'da commit + min–max clamp + normalize
		editor.focusedProperty().addListener((obs, was, isNow) -> {
			if (!isNow)
				commitEditorText(spinner, scale);
		});
		editor.setOnAction(_ -> commitEditorText(spinner, scale));
	}

	// Editor metnini değere çevir, aralığa çek, scale'e yuvarla ve normalize et
	private static void commitEditorText(Spinner<Double> spinner, int scale) {
		if (!spinner.isEditable())
			return;
		SpinnerValueFactory<Double> vf = spinner.getValueFactory();
		if (vf == null)
			return;

		TextField editor = spinner.getEditor();
		String text = editor.getText();

		Double value;
		try {
			value = vf.getConverter().fromString(text);
			if (value.isNaN() || value.isInfinite())
				value = spinner.getValue();
		} catch (Exception ex) {
			value = spinner.getValue();
		}

		// Min–max clamp (DoubleSpinnerValueFactory ise)
		if (vf instanceof SpinnerValueFactory.DoubleSpinnerValueFactory dvf) {
			double min = dvf.getMin(), max = dvf.getMax();
			if (value < min)
				value = min;
			if (value > max)
				value = max;
		}

		// UI tutarlılığı: scale'e yuvarlama (isteğe bağlı, burada açık)
		if (scale >= 0) {
			double p = Math.pow(10, scale);
			value = Math.round(value * p) / p;
		}

		vf.setValue(value); // converter ile normalize edilerek editöre yansır
	}

	/*
	 * -----------------------------------------------------------------------------
	 * ------------------------------------ Hata gösterimi (TextField / TextArea
	 * tabanlı kontroller) - setError: pseudo-class'ı basar, Label'ı görünür/gizler
	 * - attachAutoTrim: odak çıkışında trim - requireNonEmpty: boş bırakılamaz
	 * (trim'li) - bindRequired: canlı doğrulama + odak çıkışında tekrar kontrol
	 * -----------------------------------------------------------------------------
	 * ------------------------------------
	 */
	public static void setError(TextInputControl ctrl, Label errLabel, String message) {
		boolean hasError = message != null && !message.isEmpty();
		ctrl.pseudoClassStateChanged(ERROR_CLASS, hasError);

		if (errLabel != null) {
			// error-text sınıfını yalnızca bir kez ekle
			if (!errLabel.getStyleClass().contains("error-text")) {
				errLabel.getStyleClass().add("error-text");
			}
			errLabel.setText(hasError ? message : "");
			errLabel.setVisible(hasError);
			errLabel.setManaged(hasError);
		}
	}

	/** Boş bırakılamaz kontrolü (trim'li). */
	public static boolean requireNonEmpty(TextInputControl ctrl, Label errLabel, String message) {
		boolean ok = ctrl.getText() != null && !ctrl.getText().trim().isEmpty();
		setError(ctrl, errLabel, ok ? null : message);
		return ok;
	}

	/** Canlı doğrulama + odak çıkışı kontrolü tek metotta. */
	public static void bindRequired(TextInputControl ctrl, Label errLabel, String message) {
		// Başlangıçta hatayı gizle (aynı zamanda error-text stilini ekler)
		setError(ctrl, errLabel, null);

		// Kullanıcı yazdıkça kontrol
		ctrl.textProperty().addListener((o, ov, nv) -> requireNonEmpty(ctrl, errLabel, message));

		// Odak çıkışında trim + tekrar kontrol
		ctrl.focusedProperty().addListener((o, oldF, newF) -> {
			if (!newF) {
				if (ctrl.getText() != null)
					ctrl.setText(ctrl.getText().trim());
				requireNonEmpty(ctrl, errLabel, message);
			}
		});
	}
}
