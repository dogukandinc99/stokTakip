package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	private Button addProductBtn;
	@FXML
	private Button upgradeProductBtn;
	@FXML
	private Button deleteProductBtn;

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
	private ChoiceBox<String> categorychoicebox;
	@FXML
	private ChoiceBox<String> upgradeChoiceBox;
	@FXML
	private ChoiceBox<String> upgradeSearchChoiceBox;
	@FXML
	private ChoiceBox<String> mainChoiceBox;

	@FXML
	private Spinner<Integer> productquantityspinner;
	@FXML
	private Spinner<Integer> upgradeproductquantityspinner;

	@FXML
	private TableView<DataBaseHelper.VeriModel> settingstableview;
	@FXML
	private TableView<DataBaseHelper.VeriModel> addProductTableView;
	@FXML
	private TableView<DataBaseHelper.VeriModel> mainTableView;
	@FXML
	private TableView<DataBaseHelper.VeriModel> upgradeTableView;

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
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Integer> mainTableViewColumn1;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> mainTableViewColumn2;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> mainTableViewColumn3;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Integer> mainTableViewColumn4;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> mainTableViewColumn5;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Double> mainTableViewColumn6;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Integer> upgradeTableViewColumn1;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> upgradeTableViewColumn2;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> upgradeTableViewColumn3;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Integer> upgradeTableViewColumn4;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, String> upgradeTableViewColumn5;
	@FXML
	private TableColumn<DataBaseHelper.VeriModel, Double> upgradeTableViewColumn6;

	public void initialize() {
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10000, 10000,
				1);
		productquantityspinner.setValueFactory(valueFactory);
		upgradeproductquantityspinner.setValueFactory(valueFactory);

		ChoiceBoxs();

		tablokontrol();
		settingstableviewcolumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
		settingstableviewcolumn2.setCellValueFactory(new PropertyValueFactory<>("ad"));
		tableViewUpgrade(settingstableview, "kategoriler");

		addProductTableViewColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
		addProductTableViewColumn2.setCellValueFactory(new PropertyValueFactory<>("barkod"));
		addProductTableViewColumn3.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		addProductTableViewColumn4.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		addProductTableViewColumn5.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		addProductTableViewColumn6.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		tableViewUpgrade(addProductTableView, "stok");

		mainTableViewColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
		mainTableViewColumn2.setCellValueFactory(new PropertyValueFactory<>("barkod"));
		mainTableViewColumn3.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		mainTableViewColumn4.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		mainTableViewColumn5.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		mainTableViewColumn6.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		tableViewUpgrade(mainTableView, "stok");

		upgradeTableViewColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
		upgradeTableViewColumn2.setCellValueFactory(new PropertyValueFactory<>("barkod"));
		upgradeTableViewColumn3.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		upgradeTableViewColumn4.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		upgradeTableViewColumn5.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		upgradeTableViewColumn6.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		tableViewUpgrade(upgradeTableView, "stok");

		setupSearchListener(mainSearchTextbox, mainTableView);
		setupSearchListener(mainChoiceBox, mainTableView);
		setupSearchListener(upgradeSearchTextbox, upgradeTableView);
		setupSearchListener(upgradeSearchChoiceBox, upgradeTableView);

		switchForm();
		loadProductDetailsToFields();
		valueCategoriInsertDataBase();
		valueCategoryDeleteDataBase();
		valueProductUpgradeDataBase();
		valueProductInsertDataBase();
		valueProductDeleteDataBase();
	}

	private void setupSearchListener(TextField textField, TableView<DataBaseHelper.VeriModel> tableView) {
		textField.textProperty().addListener((_, _, newValue) -> {
			if (!newValue.isEmpty()) {
				searchProduct(tableView, newValue);
			}
		});
	}

	private void setupSearchListener(ChoiceBox<String> choiceBox, TableView<DataBaseHelper.VeriModel> tableView) {
		choiceBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
			searchCategory(tableView, newValue);
		});
	}

	private void tabloOlustur(String tabloName, String sql) {
		if (DataBaseHelper.tabloVarMi(tabloName)) {
			System.out.println(tabloName + " tablosu mevcut...");
		} else {
			DataBaseHelper.createTable(sql);
			System.out.println(tabloName + " tablosu eklendi...");
		}
	}

	private void tablokontrol() {

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

	private void switchForm() {
		homeBtn.setOnAction(_ -> {
			showForm(mainForm, mainTableView, "stok");
		});
		addStockBtn.setOnAction(_ -> {
			showForm(addStockForm, addProductTableView, "stok");
		});
		updateStockBtn.setOnAction(_ -> {
			showForm(updateStockForm, upgradeTableView, "stok");
		});
		settingsBtn.setOnAction(_ -> {
			showForm(settingsForm, settingstableview, "kategoriler");
		});
	}

	private void showForm(AnchorPane visibleForm, TableView<DataBaseHelper.VeriModel> tableView, String tabloAdi) {
		mainForm.setVisible(false);
		addStockForm.setVisible(false);
		updateStockForm.setVisible(false);
		settingsForm.setVisible(false);

		visibleForm.setVisible(true);

		tableViewUpgrade(tableView, tabloAdi);
	}

	private void ChoiceBoxs() {
		fillChoiceBox(mainChoiceBox);
		fillChoiceBox(categorychoicebox);
		fillChoiceBox(upgradeSearchChoiceBox);
		fillChoiceBox(upgradeChoiceBox);
	}

	private void fillChoiceBox(ChoiceBox<String> choiceBox) {
		ObservableList<DataBaseHelper.VeriModel> kategoriList = DataBaseHelper.loadData("kategoriler");
		ObservableList<String> kategoriAdlari = FXCollections.observableArrayList();
		for (DataBaseHelper.VeriModel kategori : kategoriList) {
			kategoriAdlari.add(kategori.getAd().toUpperCase());
		}
		choiceBox.setItems(kategoriAdlari);
		choiceBox.getSelectionModel().select(0);
	}

	private void tableViewUpgrade(TableView<DataBaseHelper.VeriModel> tableView, String table) {
		try {
			tableView.setItems(DataBaseHelper.loadData(table));
		} catch (Exception e) {
			System.out.println("tableViewUpgrade sorun var: " + e.getMessage());
		}
	}

	@FXML
	private void valueCategoriInsertDataBase() {
		newcategoribtn.setOnAction(_ -> {
			String categoriString = categoriTextBox.getText().trim().toLowerCase();
			if (categoriString.isEmpty()) {
				System.out.println("Kategori kısmı boş girilemez.");
			} else {
				if (!DataBaseHelper.degerVarMi("kategoriler", "ad", categoriString)) {
					DataBaseHelper.kategoriEkle("kategoriler", categoriString);
				} else {
					System.out.println("Bu kategori zaten var!");
				}
			}
			try {
				tableViewUpgrade(settingstableview, "kategoriler");
				ChoiceBoxs();
			} catch (Exception e) {
				System.out.println("valueCategoriInsertDataBase sorun var: " + e.getMessage());
			}
		});
	}

	@FXML
	private void valueCategoryDeleteDataBase() {
		deletecategoribtn.setOnAction(_ -> {
			DataBaseHelper.VeriModel selectCategory = settingstableview.getSelectionModel().getSelectedItem();
			if (settingstableview.getSelectionModel().getSelectedItem() == null) {
				System.out.println("Lütfen bir kategori seçin.");
				return;
			} else {
				DataBaseHelper.deleteCategory("kategoriler", selectCategory.getId());
			}
			tableViewUpgrade(settingstableview, "kategoriler");
			ChoiceBoxs();
		});
	}

	private void valueProductInsertDataBase() {
		addProductBtn.setOnAction(_ -> {
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
			tableViewUpgrade(addProductTableView, "stok");
		});
	}

	@FXML
	private void valueProductDeleteDataBase() {
		deleteProductBtn.setOnAction(_ -> {
			DataBaseHelper.VeriModel selectProduct = upgradeTableView.getSelectionModel().getSelectedItem();
			if (upgradeTableView.getSelectionModel().getSelectedItem() == null) {
				System.out.println("Lütfen bir kategori seçin.");
				return;
			} else {
				DataBaseHelper.deleteProduct("stok", selectProduct.getId());
			}
			tableViewUpgrade(upgradeTableView, "stok");
		});

	}

	private void loadProductDetailsToFields() {
		upgradeTableView.getSelectionModel().selectedItemProperty().addListener((_, _, yeniSecim) -> {
			if (yeniSecim != null) {
				upgradeBarkodTextBox.setText(yeniSecim.getBarkod());
				upgradeProductNameTextbox.setText(yeniSecim.getUrunAdi());
				upgradeproductquantityspinner.getValueFactory().setValue(yeniSecim.getUrunAdet());
				upgradeChoiceBox.setValue(yeniSecim.getKategori());
				upgradeCostTextbox.setText(String.valueOf(yeniSecim.getMaliyet()));
			}
		});
	}

	private void valueProductUpgradeDataBase() {
		upgradeProductBtn.setOnAction(_ -> {
			DataBaseHelper.VeriModel productList = upgradeTableView.getSelectionModel().getSelectedItem();
			if (productList != null) {
				DataBaseHelper.upgradeProduct(upgradeBarkodTextBox.getText().trim().toLowerCase(),
						upgradeProductNameTextbox.getText().trim().toLowerCase(),
						upgradeproductquantityspinner.getValue(), upgradeChoiceBox.getValue(),
						Double.parseDouble(upgradeCostTextbox.getText()), productList.getId());
			}
			tableViewUpgrade(upgradeTableView, "stok");
		});
	}

	private void searchCategory(TableView<DataBaseHelper.VeriModel> tableView, String aramaMetni) {
		try {
			if (!aramaMetni.equals("TÜMÜ")) {
				tableView.setItems(DataBaseHelper.kategoriFiltreleme(aramaMetni));

			} else if (aramaMetni.equals("TÜMÜ")) {
				tableViewUpgrade(tableView, "stok");
			}
		} catch (Exception e) {
			System.out.println("searchCategory sorun var: " + e.getMessage());
		}
	}

	private void searchProduct(TableView<DataBaseHelper.VeriModel> tableView, String aramaMetni) {
		try {
			if (aramaMetni != null && !aramaMetni.trim().isEmpty()) {
				tableView.setItems(DataBaseHelper.stoklariAra(aramaMetni));
			} else {
				System.out.println("buradayım");
				tableViewUpgrade(tableView, "stok");
			}
		} catch (Exception e) {
			System.out.println("searchProduct sorun var: " + e.getMessage());
		}
	}
}
