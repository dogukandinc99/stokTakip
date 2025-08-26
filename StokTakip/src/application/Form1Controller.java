package application;

import java.io.File;
import java.nio.channels.Pipe.SourceChannel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;

import javafx.scene.control.TableCell;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Form1Controller {
	@FXML
	private AnchorPane addStockForm;
	@FXML
	private AnchorPane mainForm;
	@FXML
	private AnchorPane settingsForm;
	@FXML
	private AnchorPane updateStockForm;

	@FXML
	private VBox solMenu;

	@FXML
	private Button updateStockBtn;
	@FXML
	private Button addStockBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private Button newcategoribtn;
	@FXML
	private Button deletecategoribtn;
	@FXML
	private Button settingsBtn;
	@FXML
	private Button addProductBtn;
	@FXML
	private Button upgradeProductBtn;
	@FXML
	private Button deleteProductBtn;
	@FXML
	private Button mainStokEkleBtn;
	@FXML
	private Button mainStokCikarBtn;
	@FXML
	private Button exportToExcelButton;

	@FXML
	private TextField categoriTextBox;
	@FXML
	private TextField barkodtextbox;
	@FXML
	private TextField costtextbox;
	@FXML
	private TextField producttextbox;
	@FXML
	private TextField mainSearchTextbox;
	@FXML
	private TextField upgradeSearchTextbox;
	@FXML
	private TextField upgradeBarkodTextBox;
	@FXML
	private TextField upgradeProductNameTextbox;
	@FXML
	private TextField upgradeCostTextbox;
	@FXML
	private TextField mainTextbox;
	@FXML
	private TextField adressTextfield;

	@FXML
	private ChoiceBox<VeriModel> categorychoicebox;
	@FXML
	private ChoiceBox<VeriModel> upgradeChoiceBox;
	@FXML
	private ChoiceBox<VeriModel> upgradeSearchChoiceBox;
	@FXML
	private ChoiceBox<VeriModel> mainChoiceBox;
	@FXML
	private ChoiceBox<VeriModel> materialsQuantityChoiceBox;
	@FXML
	private ChoiceBox<String> unitChoiceBox;
	@FXML
	private ChoiceBox<String> upgradeUnitChoiceBox;
	@FXML
	private ChoiceBox<String> currencychoicebox;

	@FXML
	private Label materialsLabel;
	@FXML
	private Label materialsQuantityLabel;

	@FXML
	private Spinner<Double> productquantityspinner;
	@FXML
	private Spinner<Double> upgradeproductquantityspinner;
	@FXML
	private Spinner<Double> materialsQuantitySpinner;
	@FXML
	private Spinner<Double> mainQuantityspinner;

	@FXML
	private TableView<VeriModel> settingstableview;
	@FXML
	private TableView<VeriModel> addProductTableView;
	@FXML
	private TableView<VeriModel> mainTableView;
	@FXML
	private TableView<VeriModel> upgradeTableView;

	@FXML
	private TableColumn<VeriModel, Integer> settingstableviewcolumn1;
	@FXML
	private TableColumn<VeriModel, String> settingstableviewcolumn2;
	@FXML
	private TableColumn<VeriModel, Integer> addProductTableViewColumn1;
	@FXML
	private TableColumn<VeriModel, String> addProductTableViewColumn2;
	@FXML
	private TableColumn<VeriModel, String> addProductTableViewColumn3;
	@FXML
	private TableColumn<VeriModel, Integer> addProductTableViewColumn4;
	@FXML
	private TableColumn<VeriModel, String> addProductTableViewColumn5;
	@FXML
	private TableColumn<VeriModel, Double> addProductTableViewColumn6;
	@FXML
	private TableColumn<VeriModel, String> addProductTableViewColumn7;
	@FXML
	private TableColumn<VeriModel, String> addProductTableViewColumn8;
	@FXML
	private TableColumn<VeriModel, Integer> mainTableViewColumn1;
	@FXML
	private TableColumn<VeriModel, String> mainTableViewColumn2;
	@FXML
	private TableColumn<VeriModel, String> mainTableViewColumn3;
	@FXML
	private TableColumn<VeriModel, Integer> mainTableViewColumn4;
	@FXML
	private TableColumn<VeriModel, String> mainTableViewColumn5;
	@FXML
	private TableColumn<VeriModel, Double> mainTableViewColumn6;
	@FXML
	private TableColumn<VeriModel, String> mainTableViewColumn7;
	@FXML
	private TableColumn<VeriModel, String> mainTableViewColumn8;
	@FXML
	private TableColumn<VeriModel, Integer> upgradeTableViewColumn1;
	@FXML
	private TableColumn<VeriModel, String> upgradeTableViewColumn2;
	@FXML
	private TableColumn<VeriModel, String> upgradeTableViewColumn3;
	@FXML
	private TableColumn<VeriModel, Integer> upgradeTableViewColumn4;
	@FXML
	private TableColumn<VeriModel, String> upgradeTableViewColumn5;
	@FXML
	private TableColumn<VeriModel, Double> upgradeTableViewColumn6;
	@FXML
	private TableColumn<VeriModel, String> upgradeTableViewColumn7;
	@FXML
	private TableColumn<VeriModel, String> upgradeTableViewColumn8;

	ObservableList<String> unitList = FXCollections.observableArrayList();
	ObservableList<String> currencyList = FXCollections.observableArrayList();
	Services services = new Services();

	public void initialize() {

		spinnerSettings();
		textFieldSettings();

		unitList.add("ADET");
		unitList.add("LİTRE");
		unitList.add("MİLİLİTRE");
		unitList.add("KİLOGRAM");
		unitList.add("GRAM");
		unitList.add("MİLİGRAM");
		unitList.add("KOLİ");
		unitList.add("PAKET");
		unitList.add("PALET");
		unitList.add("POŞET");
		unitList.add("TON");
		unitList.add("DİĞER");

		unitChoiceBox.setItems(unitList);
		unitChoiceBox.getSelectionModel().select(0);
		upgradeUnitChoiceBox.setItems(unitList);
		upgradeUnitChoiceBox.getSelectionModel().select(0);

		currencyList.add("TL");
		currencyList.add("DOLAR");
		currencyList.add("EURO");

		currencychoicebox.setItems(currencyList);
		currencychoicebox.getSelectionModel().select(0);

		ChoiceBoxs();

		services.tablolariOlustur();

		settingstableviewcolumn1.setCellValueFactory(new PropertyValueFactory<>("kategoriId"));
		settingstableviewcolumn2.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		tableViewUpgrade(settingstableview, "kategoriler");

		tableViewSettings(mainTableView);
		tableViewSettings(addProductTableView);
		addProductTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewSettings(upgradeTableView);

		setupSearchListener(mainSearchTextbox, mainTableView);
		setupSearchListener(mainChoiceBox, mainTableView);
		setupSearchListener(upgradeSearchTextbox, upgradeTableView);
		setupSearchListener(upgradeSearchChoiceBox, upgradeTableView);

		switchForm();

		categoryChoiceboxVisible();

		loadProductDetailsToFields();
		valueCategoriInsertDataBase();
		valueCategoryDeleteDataBase();
		valueProductUpgradeDataBase();
		valueProductInsertDataBase();
		valueProductDeleteDataBase();
		valueProductAddDataBase();
		exportToExcel();

		setTooltipForTableview(mainTableView);
		setTooltipForTableview(addProductTableView);
		setTooltipForTableview(upgradeTableView);
		bindSaveButton();
	}

	private void switchForm() {
		homeBtn.setOnAction(_ -> {
			showForm(mainForm, mainTableView, "ürünler");
		});
		addStockBtn.setOnAction(_ -> {
			showForm(addStockForm, addProductTableView, "ürünler");
		});
		updateStockBtn.setOnAction(_ -> {
			showForm(updateStockForm, upgradeTableView, "ürünler");
		});
		settingsBtn.setOnAction(_ -> {
			showForm(settingsForm, settingstableview, "kategoriler");
		});
	}

	// spinner ayarları için oluşturuldu.
	private void spinnerSettings() {
		ValidationUtil.applyDecimalSpinner(mainQuantityspinner, 6, 12, true);
		ValidationUtil.applyDecimalSpinner(productquantityspinner, 6, 12, true);
		ValidationUtil.applyDecimalSpinner(upgradeproductquantityspinner, 6, 12, true);
	}

	private void textFieldSettings() {
		ValidationUtil.bindRequired(barkodtextbox, null, "a");
		ValidationUtil.bindRequired(producttextbox, null, "a");
		ValidationUtil.bindRequired(costtextbox, null, "a");
		ValidationUtil.bindRequired(upgradeBarkodTextBox, null, "a");
		ValidationUtil.bindRequired(upgradeProductNameTextbox, null, "a");
		ValidationUtil.applyDecimalTextField(costtextbox, 6, 12, false);
	}

	private void bindSaveButton() {
		var invalidForm = Bindings.createBooleanBinding(() -> {
			boolean barkod = barkodtextbox.getText() == null || barkodtextbox.getText().trim().isEmpty();
			boolean product = producttextbox.getText() == null || producttextbox.getText().trim().isEmpty();
			boolean cost = costtextbox.getText() == null || costtextbox.getText().trim().isEmpty();
			Boolean categoriAll = categorychoicebox.getValue().getKategori().toLowerCase().equals("hepsi");
			var TR = new java.util.Locale("tr", "TR");
			Object v = categorychoicebox.getValue().getKategori();
			String s = (v == null) ? "" : v.toString().toLowerCase(TR);
			boolean isUrunler = s.equals("ürünler");
			boolean hasSel = addProductTableView.getSelectionModel().getSelectedItem() != null;
			Boolean sonuc = isUrunler && !hasSel;

			return barkod || product || cost || sonuc || categoriAll;
		}, barkodtextbox.textProperty(), producttextbox.textProperty(), costtextbox.textProperty(),
				categorychoicebox.valueProperty(), addProductTableView.getSelectionModel().selectedItemProperty());

		// Yeniden bağlamadan önce unbind et (aynı metoda tekrar girilirse hata olmasın)
		addProductBtn.disableProperty().bind(invalidForm);
	}

	// tablo ayarları için oluşturuldu.
	private void tableViewSettings(TableView<VeriModel> tableView) {
		ObservableList<TableColumn<VeriModel, ?>> kolonlar = tableView.getColumns();
		kolonlar.get(0).setCellValueFactory(new PropertyValueFactory<>("urunId"));
		kolonlar.get(1).setCellValueFactory(new PropertyValueFactory<>("barkod"));
		kolonlar.get(2).setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		kolonlar.get(3).setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		kolonlar.get(4).setCellValueFactory(new PropertyValueFactory<>("birim"));
		TableColumn<VeriModel, String> kategoriCol = (TableColumn<VeriModel, String>) kolonlar.get(5);
		kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		kategoriCol.setCellFactory(col -> new TableCell<VeriModel, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					// Türkçe büyük harf dönüşümü için TR locale kullan
					setText(item.toUpperCase(new java.util.Locale("tr", "TR")));
				}
			}
		});
		TableColumn<VeriModel, Double> maliyetKolon = (TableColumn<VeriModel, Double>) kolonlar.get(6);
		maliyetKolon.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		maliyetKolon.setCellFactory(_ -> new TableCell<VeriModel, Double>() {

			@Override
			protected void updateItem(Double item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else { // Burada maliyeti iki ondalıklı olarak gösterebilirsiniz
					DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
					DecimalFormat decimalFormat = new DecimalFormat("#.#######", symbols);
					setText(decimalFormat.format(item));
				}
			}
		});
		TableColumn<VeriModel, String> paraBirimiCol = (TableColumn<VeriModel, String>) kolonlar.get(7);
		paraBirimiCol.setCellValueFactory(new PropertyValueFactory<>("paraBirimi"));
		paraBirimiCol.setCellFactory(col -> new TableCell<VeriModel, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					// Türkçe büyük harf dönüşümü için TR locale kullan
					setText(item.toUpperCase(new java.util.Locale("tr", "TR")));
				}
			}
		});

		tableViewUpgrade(tableView, "ürünler");
	}

	// sayfalar arası geçiş için oluşturuldu.
	private void showForm(AnchorPane visibleForm, TableView<VeriModel> tableView, String tabloAdi) {
		mainForm.setVisible(false);
		addStockForm.setVisible(false);
		updateStockForm.setVisible(false);
		settingsForm.setVisible(false);

		visibleForm.setVisible(true);

		tableViewUpgrade(tableView, tabloAdi);
	}

	// kategorileri choicebxlara koymak için oluşturuldu.
	private void ChoiceBoxs() {
		fillChoiceBox(mainChoiceBox);
		fillChoiceBox(categorychoicebox);
		fillChoiceBox(upgradeSearchChoiceBox);
		fillChoiceBox(upgradeChoiceBox);
	}

	// kategorileri veritabanından çekmek için oluşturuldu.
	private void fillChoiceBox(ChoiceBox<VeriModel> choiceBox) {
		ObservableList<VeriModel> kategoriList = services.ürünListele("kategoriler");

		choiceBox.setConverter(new StringConverter<VeriModel>() {
			@Override
			public String toString(VeriModel kategori) {
				return (kategori != null && kategori.getKategori() != null) ? kategori.getKategori().toUpperCase() : "";
			}

			@Override
			public VeriModel fromString(String string) {
				return null;
			}
		});

		choiceBox.setItems(kategoriList);

		if (!kategoriList.isEmpty()) {
			choiceBox.getSelectionModel().select(0);
		}
	}

	// tabloları güncellemek için oluşturuldu
	private void tableViewUpgrade(TableView<VeriModel> tableView, String table) {
		try {
			tableView.setItems(services.ürünListele(table));
			tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		} catch (Exception e) {
			System.out.println("tableViewUpgrade sorun var: " + e.getMessage());
		}
	}

	// kategori eklemek için oluşturuldu.
	private void valueCategoriInsertDataBase() {
		newcategoribtn.setOnAction(_ -> {
			String categoriString = categoriTextBox.getText().trim().toLowerCase();
			if (categoriString.isEmpty()) {
				information("Bilgi", null, "Kategori ismi boş girilemez.", AlertType.INFORMATION);
			} else {
				services.kategoriEkle(categoriString);
			}
			try {
				tableViewUpgrade(settingstableview, "kategoriler");
				ChoiceBoxs();
			} catch (Exception e) {
				System.out.println("valueCategoriInsertDataBase sorun var: " + e.getMessage());
			}
		});
	}

	// kategori silmek için oluşturuldu.
	@FXML
	private void valueCategoryDeleteDataBase() {
		deletecategoribtn.setOnAction(_ -> {
			VeriModel selectCategory = settingstableview.getSelectionModel().getSelectedItem();
			if (settingstableview.getSelectionModel().getSelectedItem() == null) {
				System.out.println("Lütfen bir kategori seçin.");
				return;
			} else {
				services.kategoriSil(selectCategory.getKategoriId());
			}
			tableViewUpgrade(settingstableview, "kategoriler");
			ChoiceBoxs();
		});
	}

	// ürünler kategorisi seçildiğinde maliyet girilip girilememesini engellemek
	// için oluşturuldu.
	void categoryChoiceboxVisible() {
		categorychoicebox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
			if (newValue != null) {
				VeriModel categoryChoiceBoxValue = newValue;
				if (categoryChoiceBoxValue.getKategori().equals("ürünler")) {
					costtextbox.setDisable(true);
				} else {
					costtextbox.setDisable(false);
				}
			}
		});
	}

	// ürünleri veritabanına eklemek için oluşturuldu.
	private void valueProductInsertDataBase() {
		addProductBtn.setOnAction(_ -> {
			String barkod = barkodtextbox.getText().trim().toLowerCase();
			String ürünAdi = producttextbox.getText().trim().toLowerCase();
			Double ürünAdet = productquantityspinner.getValue();
			String birim = unitChoiceBox.getValue();
			VeriModel category = categorychoicebox.getValue();
			Double maliyet = 0.0;
			String paraBirimi = currencychoicebox.getValue();

			System.out.println(costtextbox.getText());
			if (!costtextbox.getText().isEmpty()) {
				maliyet = Double.parseDouble(costtextbox.getText());
			}

			if (category.getKategori().equals("hepsi")) {
				System.out.println("Kategori Seçimi yapmanız gerekmektedir.");
			} else if (!category.getKategori().equals("ürünler")) {
				if (barkod.isEmpty() && ürünAdi.isEmpty() && ürünAdet == 0 && maliyet == 0) {
					System.out.println("Bazı alanlar boş...");
				} else {
					services.ürünEkle(barkod, ürünAdi, ürünAdet, birim, category.getKategori(), maliyet, paraBirimi);
				}
			} else if (category.getKategori().equals("ürünler")) {
				if (barkod.isEmpty() && ürünAdi.isEmpty() && ürünAdet == 0 && maliyet == 0) {
					System.out.println("Bazı alanlar boş...");
				} else {
					ObservableList<VeriModel> tableSelectedList = addProductTableView.getSelectionModel()
							.getSelectedItems();
					if (tableSelectedList.size() < 1) {
						System.out.println("Tablodan Ham Madde ve/veya Ambalaj Seçimi Yapmanız Gerekmektedir...");
					} else {
						boolean kontrol = false;
						for (int i = 0; i < tableSelectedList.size(); i++) {
							if (!tableSelectedList.get(i).getKategori().toLowerCase().equals("ambalajlar")
									&& !tableSelectedList.get(i).getKategori().toLowerCase().equals("ham maddeler")) {

								kontrol = true;
							}
						}
						try {
							if (!kontrol) {
								valueProductMaterialsInsertDataBase(barkod, ürünAdi, ürünAdet, birim,
										category.getKategori(), paraBirimi, tableSelectedList);
							} else {
								information("Bilgi", null,
										"Ürünü ekleyebilmek için tablodan Ham Madde ve/veya Ambalaj seçimi yapmanız gerekmektedir...",
										AlertType.INFORMATION);
							}

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}
			}
			tableViewUpgrade(addProductTableView, "ürünler");
			return;
		});
	}

	// ürünün stoğuna ekleme veya çıkarma yapabilmek için oluşturuldu.
	private void valueProductAddDataBase() {
		mainStokEkleBtn.setOnAction(_ -> {
			ObservableList<VeriModel> ürün = mainTableView.getSelectionModel().getSelectedItems();
			if (!ürün.isEmpty()) {
				double toplam = ürün.get(0).getUrunAdet();
				toplam += mainQuantityspinner.getValue();
				services.ürünGüncelle(ürün.get(0).getUrunId(), ürün.get(0).getBarkod(), ürün.get(0).getUrunAdi(),
						toplam, ürün.get(0).getBirim(), ürün.get(0).getKategori(), ürün.get(0).getMaliyet());
				tableViewUpgrade(mainTableView, "ürünler");
			} else {
				information("Bilgi", null,
						"Stok adeti değiştirebilmek için listeden bir ürün seçmeniz gerekmektedir...",
						AlertType.INFORMATION);
				return;
			}
		});
	}

	// ürün eklerken ham madde ve ambalaj seçileceğinden dolayı
	// yeni bir form oluştursun ve veritabanına eklesin diye oluşturuldu.
	void valueProductMaterialsInsertDataBase(String barkod, String urunAdi, Double urunAdet, String birim,
			String kategori, String paraBirimi, ObservableList<VeriModel> selectedMaterials) {

		Stage stage = new Stage();
		VBox vBox = new VBox(10);
		vBox.setPadding(new Insets(10));

		Label titleLabel = new Label("Hammadde Miktarlarını Girin:");
		vBox.getChildren().add(titleLabel);

		List<Spinner<Double>> spinners = new ArrayList<>();
		List<VeriModel> selectedItems = new ArrayList<>();

		for (VeriModel material : selectedMaterials) {
			Label label = new Label(material.getUrunAdi() + ":");
			Label birimlb = new Label(material.getBirim().toUpperCase());
			Spinner<Double> spinner = new Spinner<>();
			spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 1));
			spinners.add(spinner);
			selectedItems.add(material);
			HBox row = new HBox(10, label, spinner, birimlb);
			vBox.getChildren().add(row);
		}

		Button saveButton = new Button("Kaydet");

		saveButton.setOnAction(_ -> {
			boolean kontrol = true;

			for (VeriModel material : selectedItems) {
				System.out.println(material.getKategori().toString());
				if (!(material.getKategori().toString().equals("ham maddeler")
						|| material.getKategori().toString().equals("ambalajlar"))) {
					kontrol = false;
					break;
				}
			}
			try {
				if (kontrol) {
					double maliyetToplam = 0;

					for (int i = 0; i < selectedItems.size(); i++) {
						services.içindekileriEkle(selectedItems.get(i).getUrunId(),
								(spinners.get(i).getValue()).doubleValue(), selectedItems.get(i).getBirim());
						maliyetToplam += (selectedItems.get(i).getUrunAdet() * selectedItems.get(i).getMaliyet());
					}
					services.ürünEkle(barkod, urunAdi, urunAdet, birim, kategori, maliyetToplam, paraBirimi);
					stage.close();
				} else {
					System.out.println("Sadece ham madde veya ambalaj ekleyebilirsiniz.");
				}

			} catch (Exception ex) {
				System.out.println("Hata: " + ex.getMessage());
			}
		});

		vBox.getChildren().add(saveButton);

		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.setTitle("Hammadde Seçimi");
		stage.showAndWait();
	}

	// ürün silmek için oluşturuldu.
	private void valueProductDeleteDataBase() {
		deleteProductBtn.setOnAction(_ -> {
			VeriModel selectProduct = upgradeTableView.getSelectionModel().getSelectedItem();
			if (upgradeTableView.getSelectionModel().getSelectedItem() == null) {
				System.out.println("Lütfen bir kategori seçin.");
				return;
			} else {
				services.ürünSil(selectProduct.getUrunId());
			}
			tableViewUpgrade(upgradeTableView, "ürünler");
		});

	}

	// güncelleme yaparken seçilen ürünün bilgileri textfieldları doldurması için
	// oluşturuldu.
	private void loadProductDetailsToFields() {
		upgradeTableView.getSelectionModel().selectedItemProperty().addListener((_, _, yeniSecim) -> {
			if (yeniSecim != null) {
				upgradeBarkodTextBox.setText(yeniSecim.getBarkod());
				upgradeProductNameTextbox.setText(yeniSecim.getUrunAdi());
				upgradeproductquantityspinner.getValueFactory().setValue(yeniSecim.getUrunAdet());
				upgradeUnitChoiceBox.getSelectionModel().select(yeniSecim.getBirim().toString());
				ObservableList<VeriModel> kategoriList = services.ürünListele("kategoriler");
				for (VeriModel veri : kategoriList) {
					if (yeniSecim.getKategori().toLowerCase().equals(veri.getKategori())) {
						upgradeChoiceBox.getSelectionModel().select(veri.getKategoriId() - 1);
					}
				}
				DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
				DecimalFormat decimalFormat = new DecimalFormat("#.#######", symbols); // Ondalık kısmı dinamik
				upgradeCostTextbox.setText(decimalFormat.format(yeniSecim.getMaliyet()));
			}
		});
		mainTableView.getSelectionModel().selectedItemProperty().addListener((_, _, yeniSecim) -> {
			if (yeniSecim != null) {
				mainTextbox.setText(yeniSecim.getUrunAdi());
			}
		});
	}

	// ürünleri güncellemek için oluşturuldu
	private void valueProductUpgradeDataBase() {
		upgradeProductBtn.setOnAction(_ -> {
			VeriModel productList = upgradeTableView.getSelectionModel().getSelectedItem();
			if (productList != null) {
				if (productList.getKategori().toLowerCase().equals("ürünler")) {
					services.ürünGüncelle(productList.getUrunId(), upgradeBarkodTextBox.getText().trim().toLowerCase(),
							upgradeProductNameTextbox.getText().trim().toLowerCase(),
							upgradeproductquantityspinner.getValue(), upgradeUnitChoiceBox.getValue(),
							upgradeChoiceBox.getValue().getKategori(),
							Double.parseDouble(upgradeCostTextbox.getText()));
				} else if (productList.getKategori().toLowerCase().equals("ham maddeler")
						|| productList.getKategori().toLowerCase().equals("ambalajlar")) {
					if (productList.getMaliyet() != Double.parseDouble(upgradeCostTextbox.getText())) {
						services.maliyetGüncelle(productList.getUrunId(),
								Double.parseDouble(upgradeCostTextbox.getText()));
					}
					services.ürünGüncelle(productList.getUrunId(), upgradeBarkodTextBox.getText().trim().toLowerCase(),
							upgradeProductNameTextbox.getText().trim().toLowerCase(),
							upgradeproductquantityspinner.getValue(), upgradeUnitChoiceBox.getValue(),
							upgradeChoiceBox.getValue().getKategori(),
							Double.parseDouble(upgradeCostTextbox.getText()));
				} else {
					services.ürünGüncelle(productList.getUrunId(), upgradeBarkodTextBox.getText().trim().toLowerCase(),
							upgradeProductNameTextbox.getText().trim().toLowerCase(),
							upgradeproductquantityspinner.getValue(), upgradeUnitChoiceBox.getValue(),
							upgradeChoiceBox.getValue().getKategori(),
							Double.parseDouble(upgradeCostTextbox.getText()));
				}
			} else {
				information("Bilgi", null, "Güncelleme yapabilmek için listeden bir ürün seçmeniz gerekmektedi...",
						AlertType.INFORMATION);
			}
			tableViewUpgrade(upgradeTableView, "ürünler");
		});
	}

	// textfielden ürünleri çekip aramak için oluşturuldu
	private void setupSearchListener(TextField textField, TableView<VeriModel> tableView) {
		textField.textProperty().addListener((_, _, newValue) -> {
			if (!newValue.isEmpty()) {
				searchProduct(tableView, newValue);
			}
		});
	}

	// kategori choiceboxdan ürünleri listelemek için oluşturuldu.
	private void setupSearchListener(ChoiceBox<VeriModel> choiceBox, TableView<VeriModel> tableView) {
		choiceBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
			if (newValue != null) {
				searchCategory(tableView, newValue.getKategori());
			}
		});
	}

	// ürünleri listelemek için oluşturuldu
	private void searchCategory(TableView<VeriModel> tableView, String aramaMetni) {
		try {
			if (!aramaMetni.equals("hepsi")) {
				tableView.setItems(services.kategoriFiltrele("ürünler", aramaMetni));
			} else if (aramaMetni.equals("hepsi")) {
				tableViewUpgrade(tableView, "ürünler");
			}
		} catch (Exception e) {
			System.out.println("search Category sorun var: " + e.getMessage());
		}
	}

	// kategori bazlı ürünleri listelemek için oluşturuldu
	private void searchProduct(TableView<VeriModel> tableView, String aramaMetni) {
		try {
			if (aramaMetni != null && !aramaMetni.trim().isEmpty()) {
				tableView.setItems(services.stokAra("ürünler", aramaMetni));
			} else {
				tableViewUpgrade(tableView, "ürünler");
			}
		} catch (Exception e) {
			System.out.println("search Product sorun var: " + e.getMessage());
		}
	}

	// ürünün üstüne gelince detaylarını görmek için oluşturuldu
	private void setTooltipForTableview(TableView<VeriModel> tableView) {
		tableView.setRowFactory(_ -> {
			TableRow<VeriModel> row = new TableRow<>();
			row.setOnMouseEntered(_ -> {
				if (!row.isEmpty()) {
					VeriModel urun = row.getItem();
					String hamMaddelerString = DataBaseHelper.getHamMaddeler(urun.getUrunId());
					if (!hamMaddelerString.isEmpty()) {
						Tooltip tooltip = new Tooltip(hamMaddelerString);
						Tooltip.install(row, tooltip);
					}
				}
			});
			return row;
		});
	}

	// excel çıktısı almak için oluşturuldu
	private void exportToExcel() {
		exportToExcelButton.setOnAction(_ -> {
			Boolean kontrol = false;
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Kaydedilecek klasörü seç...");

			File desktopDir = new File(System.getProperty("user.home"), "Desktop");
			if (desktopDir.exists()) {
				chooser.setInitialDirectory(desktopDir);
			}

			File secilen = chooser.showDialog(exportToExcelButton.getScene().getWindow());
			if (secilen != null) {
				adressTextfield.setText(secilen.getAbsolutePath());
				System.out.println("adres: " + secilen.getAbsoluteFile());
				kontrol = services.exceleAktar(secilen, "stok_raporu.xls");
				if (kontrol) {
					information("Bilgi...", null, "Excel dosyası başarıyla kaydedildi", AlertType.INFORMATION);
				}
			}
		});
	}

	// hata oluştuğunda mesajları göstermek için oluşturuldu.
	private void information(String title, String header, String mesaj, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(mesaj);
		alert.showAndWait();
	}
}
