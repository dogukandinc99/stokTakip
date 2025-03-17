package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
	private Button addproductbtn;

	@FXML
	private TextField categoriTextBox;

	@FXML
	private TextField barkodtextbox;

	@FXML
	private TextField costtextbox;

	@FXML
	private TextField producttextbox;

	@FXML
	private ChoiceBox<String> categorychoicebox;

	@FXML
	private Spinner<Integer> productquantityspinner;

	@FXML
	private TableView<DataBaseHelper.VeriModel> settingstableview;
	@FXML
	private TableView<DataBaseHelper.VeriModel> addProductTableView;

	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Integer> settingstableviewcolumn1;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> settingstableviewcolumn2;

	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Integer> addProductTableViewColumn1;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> addProductTableViewColumn2;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> addProductTableViewColumn3;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Integer> addProductTableViewColumn4;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> addProductTableViewColumn5;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Double> addProductTableViewColumn6;

	public void initialize() {
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10000, 10000,
				1);
		productquantityspinner.setValueFactory(valueFactory);

		tablokontrol();
		settingstableviewcolumn1.setCellValueFactory(new PropertyValueFactory<>("id")); // id'yi bağladık
		settingstableviewcolumn2.setCellValueFactory(new PropertyValueFactory<>("ad")); // ad'ı bağladık
		settingstableview.setItems(DataBaseHelper.loadData("kategoriler")); // Verileri yükleme

		addProductTableViewColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
		addProductTableViewColumn2.setCellValueFactory(new PropertyValueFactory<>("barkod"));
		addProductTableViewColumn3.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		addProductTableViewColumn4.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		addProductTableViewColumn5.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		addProductTableViewColumn6.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		addProductTableView.setItems(DataBaseHelper.loadData("stok"));
	}

	public void tablokontrol() {

		tabloOlustur("kategoriler", """
					    CREATE TABLE IF NOT EXISTS kategoriler (
				        id INTEGER PRIMARY KEY AUTOINCREMENT,
				        ad TEXT NOT NULL
				    );
				""");
		tabloOlustur("stok", """
					    CREATE TABLE IF NOT EXISTS stok (
				        id INTEGER PRIMARY KEY AUTOINCREMENT,
				        barkod TEXT UNIQUE NOT NULL,
				 		urun_adi TEXT NOT NULL,
				 		urun_adet INTEGER NOT NULL,
				 		kategori TEXT NOT NULL,
				 		maliyet REAL NOT NULL
				    );
				""");
	}

	private void tabloOlustur(String tabloName, String sql) {
		if (DataBaseHelper.tabloVarMi(tabloName)) {
			System.out.println(tabloName + " tablosu mevcut...");
		} else {
			DataBaseHelper.createTable(sql);
			System.out.println(tabloName + " tablosu eklendi...");
		}
	}

	@FXML
	public void switchForm(ActionEvent event) {
		if (event.getSource() == homeBtn) {
			mainForm.setVisible(true);
			addStockForm.setVisible(false);
			updateStockForm.setVisible(false);
			settingsForm.setVisible(false);
		} else if (event.getSource() == addStockBtn) {
			mainForm.setVisible(false);
			addStockForm.setVisible(true);
			updateStockForm.setVisible(false);
			settingsForm.setVisible(false);
			fillChoiceBox(categorychoicebox);
		} else if (event.getSource() == updateStockBtn) {
			mainForm.setVisible(false);
			addStockForm.setVisible(false);
			updateStockForm.setVisible(true);
			settingsForm.setVisible(false);
		} else if (event.getSource() == settingsBtn) {
			mainForm.setVisible(false);
			addStockForm.setVisible(false);
			updateStockForm.setVisible(false);
			settingsForm.setVisible(true);
		}
	}

	@FXML
	public void fillChoiceBox(ChoiceBox<String> choiceBox) {
		ObservableList<DataBaseHelper.VeriModel> kategoriList = DataBaseHelper.loadData("kategoriler");

		// Kategori adlarını içeren bir listeye dönüştür
		ObservableList<String> kategoriAdlari = FXCollections.observableArrayList();
		for (DataBaseHelper.VeriModel kategori : kategoriList) {
			kategoriAdlari.add(kategori.getAd().toUpperCase());
		}
		choiceBox.setItems(kategoriAdlari);
		choiceBox.getSelectionModel().select(0);
	}

	@FXML
	public void valueCategoriInsertDataBase(ActionEvent event) {
		String categoriString = categoriTextBox.getText().trim().toLowerCase();
		if (categoriString.isEmpty()) {
			System.out.println("Kategori kısmı boş girilemez.");
		} else {
			if (!DataBaseHelper.degerVarMi("kategoriler", "ad", categoriString)) {
				DataBaseHelper.kategoriEkle("kategoriler", categoriString);
				fillChoiceBox(categorychoicebox);
			} else {
				System.out.println("Bu kategori zaten var!");
			}
		}
		try {
			settingstableview.setItems(DataBaseHelper.loadData("kategoriler")); // Verileri yükleme
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sorun var");
		}

	}

	@FXML
	public void valueProductInsertDataBase(ActionEvent event) {
		String barkod = barkodtextbox.getText().trim().toLowerCase();
		String ürünAdi = producttextbox.getText().trim().toLowerCase();
		Integer ürünAdet = productquantityspinner.getValue();
		String category = categorychoicebox.getValue();
		Double maliyet = Double.parseDouble(costtextbox.getText());
		if (barkod.isEmpty() && ürünAdi.isEmpty() && ürünAdet == 0 && maliyet == 0) {
			System.out.println("Bazı alanlar boş...");
		} else {
			if (!DataBaseHelper.degerVarMi("stok", "urun_adi", ürünAdi)) {
				DataBaseHelper.addProduct("stok", barkod, ürünAdi, ürünAdet, category, maliyet);
			} else {
				System.out.println("Bu kategori zaten var!");
			}
		}
		try {
			addProductTableView.setItems(DataBaseHelper.loadData("stok")); // Verileri yükleme
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sorun var");
		}
	}

	@FXML
	public void valueDeleteDataBase(ActionEvent event) {
		DataBaseHelper.VeriModel selectCategory = settingstableview.getSelectionModel().getSelectedItem();
		if (settingstableview.getSelectionModel().getSelectedItem() == null) {
			System.out.println("Lütfen bir kategori seçin.");
			return;// Eğer kategori seçilmemişse işlemi durdur
		} else {
			DataBaseHelper.categorySil("kategoriler", selectCategory.getId());
		}
		initialize();
	}

}
