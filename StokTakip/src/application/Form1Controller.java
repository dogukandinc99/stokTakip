package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	private ChoiceBox<DataBaseHelper.VeriModel> categorychoicebox;
	@FXML
	private ChoiceBox<DataBaseHelper.VeriModel> upgradeChoiceBox;
	@FXML
	private ChoiceBox<DataBaseHelper.VeriModel> upgradeSearchChoiceBox;
	@FXML
	private ChoiceBox<DataBaseHelper.VeriModel> mainChoiceBox;
	@FXML
	private ChoiceBox<DataBaseHelper.VeriModel> materialsQuantityChoiceBox;
	@FXML
	private ChoiceBox<String> unitChoiceBox;

	@FXML
	private Label materialsLabel;
	@FXML
	private Label materialsQuantityLabel;

	@FXML
	private Spinner<Integer> productquantityspinner;
	@FXML
	private Spinner<Integer> upgradeproductquantityspinner;
	@FXML
	private Spinner<Integer> materialsQuantitySpinner;

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

		ObservableList<String> unitList = FXCollections.observableArrayList();
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
		addProductTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewUpgrade(addProductTableView, "ürünler");

		mainTableViewColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
		mainTableViewColumn2.setCellValueFactory(new PropertyValueFactory<>("barkod"));
		mainTableViewColumn3.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		mainTableViewColumn4.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		mainTableViewColumn5.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		mainTableViewColumn6.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		tableViewUpgrade(mainTableView, "ürünler");

		upgradeTableViewColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
		upgradeTableViewColumn2.setCellValueFactory(new PropertyValueFactory<>("barkod"));
		upgradeTableViewColumn3.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		upgradeTableViewColumn4.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		upgradeTableViewColumn5.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		upgradeTableViewColumn6.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		tableViewUpgrade(upgradeTableView, "ürünler");

		setupSearchListener(mainSearchTextbox, mainTableView);
		setupSearchListener(mainChoiceBox, mainTableView);
		setupSearchListener(upgradeSearchTextbox, upgradeTableView);
		setupSearchListener(upgradeSearchChoiceBox, upgradeTableView);

		switchForm();

		categoryChoiceboxVisible();

		loadProductDetailsToFields();
		valueCategoriInsertDataBase();
		valueCategoryDeleteDataBase();
		// valueProductUpgradeDataBase();
		valueProductInsertDataBase();
		valueProductDeleteDataBase();

	}

	private void tablokontrol() {

		tabloOlustur("kategoriler", """
					    CREATE TABLE IF NOT EXISTS kategoriler (
				        id INTEGER PRIMARY KEY AUTOINCREMENT,
				        ad TEXT NOT NULL
				    );
				""");
		tabloOlustur("ürünler", """
						CREATE TABLE IF NOT EXISTS ürünler (
						id INTEGER PRIMARY KEY AUTOINCREMENT,
						barkod TEXT UNIQUE NOT NULL,
						urun_adi TEXT NOT NULL,
						urun_adet INTEGER NOT NULL DEFAULT 0,
						birim TEXT NOT NULL,
						kategori_id INTEGER NOT NULL,
						maliyet REAL NOT NULL,
						FOREIGN KEY (kategori_id) REFERENCES kategoriler(id) ON DELETE CASCADE
					);
				""");
		tabloOlustur("product_ingredients", """
						CREATE TABLE IF NOT EXISTS product_ingredients (
				    	urun_id INTEGER NOT NULL,  -- Nihai ürün ID
				    	hammadde_id INTEGER NOT NULL, -- Kullanılan ham madde ID
				   		miktar REAL NOT NULL,       -- Kullanılan miktar
				   		birim TEXT NOT NULL,
				    	PRIMARY KEY (urun_id, hammadde_id),  -- Duplicate kayıtları önler
				    	FOREIGN KEY (urun_id) REFERENCES ürünler(id) ON DELETE CASCADE,
				    	FOREIGN KEY (hammadde_id) REFERENCES ürünler(id) ON DELETE CASCADE
				);
				""");

	}

	private void tabloOlustur(String tabloName, String sql) {
		if (DataBaseHelper.tabloVarMi(tabloName)) {
			System.out.println(tabloName + " tablosu mevcut...");
		} else {
			DataBaseHelper.createTable(sql, tabloName);
			System.out.println(tabloName + " tablosu eklendi...");
		}
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

	private void fillChoiceBox(ChoiceBox<DataBaseHelper.VeriModel> choiceBox) {
		ObservableList<DataBaseHelper.VeriModel> kategoriList = DataBaseHelper.loadData("kategoriler");
		choiceBox.setItems(kategoriList);

		choiceBox.getSelectionModel().select(0);
		choiceBox.setConverter(new StringConverter<DataBaseHelper.VeriModel>() {
			@Override
			public String toString(DataBaseHelper.VeriModel kategori) {
				return kategori.getAd().toUpperCase(); // Kullanıcıya sadece adını göster
			}

			@Override
			public DataBaseHelper.VeriModel fromString(String string) {
				return null; // Gerek yok
			}
		});

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

	void categoryChoiceboxVisible() {
		categorychoicebox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			DataBaseHelper.VeriModel categoryChoiceBoxValue = newValue;
			if (categoryChoiceBoxValue.getAd().equals("ürünler")) {
				materialsLabel.setVisible(true);
				materialsQuantityChoiceBox.setVisible(true);
				materialsQuantityLabel.setVisible(true);
				materialsQuantitySpinner.setVisible(true);
			} else {
				materialsLabel.setVisible(false);
				materialsQuantityChoiceBox.setVisible(false);
				materialsQuantityLabel.setVisible(false);
				materialsQuantitySpinner.setVisible(false);
			}
		});
	}

	private void valueProductInsertDataBase() {

		addProductBtn.setOnAction(_ -> {
			String barkod = barkodtextbox.getText().trim().toLowerCase();
			String ürünAdi = producttextbox.getText().trim().toLowerCase();
			Integer ürünAdet = productquantityspinner.getValue();
			String birim = unitChoiceBox.getValue();
			DataBaseHelper.VeriModel category = categorychoicebox.getValue();
			Double maliyet = 0.0;
			try {
				maliyet = Double.parseDouble(costtextbox.getText());
			} catch (NumberFormatException e) {
				System.out.println("Geçerli bir maliyet değeri girin.");
				return;
			}

			if (!gecerliMi(barkod, ürünAdi, ürünAdet, birim, category, maliyet)) {
				return;
			}

			if (category.getAd().equals("hepsi")) {
				System.out.println("Kategori Seçimi yapmanız gerekmektedir.");
			} else if (!category.getAd().equals("ürünler")) {
				if (barkod.isEmpty() && ürünAdi.isEmpty() && ürünAdet == 0 && maliyet == 0) {
					System.out.println("Bazı alanlar boş...");
				} else {
					if (!DataBaseHelper.degerVarMi("ürünler", "urun_adi", ürünAdi)) {
						DataBaseHelper.addProduct(barkod, ürünAdi, ürünAdet, birim, category.getId(), maliyet);
					} else {
						System.out.println("Bu ürün zaten var!");
					}
				}
			} else if (category.getAd().equals("ürünler")) {
				if (barkod.isEmpty() && ürünAdi.isEmpty() && ürünAdet == 0 && maliyet == 0) {
					System.out.println("Bazı alanlar boş...");
				} else {
					if (!DataBaseHelper.degerVarMi("ürünler", "urun_adi", ürünAdi)) {
						ObservableList<DataBaseHelper.VeriModel> tableSelectedList = addProductTableView
								.getSelectionModel().getSelectedItems();
						if (tableSelectedList.size() < 1) {
							System.out.println("Tablodan Ham Madde Seçimi Yapmanız Gerekmektedir...");
						} else {
							try {
								int lastId = (DataBaseHelper.getLastInsertedProductId() + 1);
								if (valueProductMaterialsInsertDataBase(lastId, tableSelectedList)) {
									DataBaseHelper.addProduct(barkod, ürünAdi, ürünAdet, birim, category.getId(),
											maliyet);
								} else {
									System.out.println("sorun var.");
								}
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
					} else {
						System.out.println("Bu ürün zaten var!");
					}
				}
			}
			tableViewUpgrade(addProductTableView, "ürünler");
			return;
		});
	}

	private boolean gecerliMi(String barkod, String ürünAdi, Integer ürünAdet, String birim,
			DataBaseHelper.VeriModel category, Double maliyet) {
		if (category == null || category.getAd().equals("hepsi")) {
			System.out.println("Kategori seçimi yapmanız gerekmektedir.");
			return false;
		}

		if (barkod.isEmpty() || ürünAdi.isEmpty() || ürünAdet == null || ürünAdet <= 0 || maliyet == null
				|| maliyet <= 0) {
			System.out.println("Lütfen tüm alanları eksiksiz ve doğru doldurun. /n" + barkod + " " + ürünAdi + " "
					+ ürünAdet.toString() + " " + maliyet.toString());
			return false;
		}

		return true;
	}

	boolean valueProductMaterialsInsertDataBase(int urun_id,
			ObservableList<DataBaseHelper.VeriModel> selectedMaterials) {
		Stage stage = new Stage();
		VBox vBox = new VBox(10);
		vBox.setPadding(new Insets(10));

		Label titleLabel = new Label("Hammadde Miktarlarını Girin:");
		vBox.getChildren().add(titleLabel);

		List<Spinner<Integer>> spinners = new ArrayList<>();
		List<DataBaseHelper.VeriModel> selectedItems = new ArrayList<>();

		for (DataBaseHelper.VeriModel material : selectedMaterials) {
			Label label = new Label(material.getUrunAdi() + ":");
			Spinner<Integer> spinner = new Spinner<Integer>(0, 10000, 1);
			spinners.add(spinner);
			selectedItems.add(material);
			HBox row = new HBox(10, label, spinner);
			vBox.getChildren().add(row);
		}

		final boolean[] isSave = { false };
		Button saveButton = new Button("Kaydet");
		saveButton.setOnAction(e -> {
			boolean kontrol = true;

			for (DataBaseHelper.VeriModel material : selectedItems) {
				System.out.println(material.getKategori().toString());
				if (!(material.getKategori().equals("HAM MADDELER") || material.getKategori().equals("AMBALAJLAR"))) {
					kontrol = false;
					break;
				}
			}
			try {
				if (kontrol) {
					for (int i = 0; i < selectedItems.size(); i++) {
						DataBaseHelper.addProductIngredients(urun_id, selectedItems.get(i).getId(),
								spinners.get(i).getValue(), selectedItems.get(i).getBirim());
					}
					isSave[0] = true;
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

		return isSave[0];
	}

	private void valueProductDeleteDataBase() {
		deleteProductBtn.setOnAction(_ -> {
			DataBaseHelper.VeriModel selectProduct = upgradeTableView.getSelectionModel().getSelectedItem();
			if (upgradeTableView.getSelectionModel().getSelectedItem() == null) {
				System.out.println("Lütfen bir kategori seçin.");
				return;
			} else {
				DataBaseHelper.deleteProduct("ürünler", selectProduct.getId());
			}
			tableViewUpgrade(upgradeTableView, "ürünler");
		});

	}

	private void loadProductDetailsToFields() {
		upgradeTableView.getSelectionModel().selectedItemProperty().addListener((_, _, yeniSecim) -> {
			if (yeniSecim != null) {
				upgradeBarkodTextBox.setText(yeniSecim.getBarkod());
				upgradeProductNameTextbox.setText(yeniSecim.getUrunAdi());
				upgradeproductquantityspinner.getValueFactory().setValue(yeniSecim.getUrunAdet());
				upgradeChoiceBox.getSelectionModel().select((Integer.parseInt(yeniSecim.getKategori()) - 1));
				upgradeCostTextbox.setText(String.valueOf(yeniSecim.getMaliyet()));
			}
		});
	}

	private void setupSearchListener(TextField textField, TableView<DataBaseHelper.VeriModel> tableView) {
		textField.textProperty().addListener((_, _, newValue) -> {
			if (!newValue.isEmpty()) {
				searchProduct(tableView, newValue);
			}
		});
	}

	private void setupSearchListener(ChoiceBox<DataBaseHelper.VeriModel> choiceBox,
			TableView<DataBaseHelper.VeriModel> tableView) {
		choiceBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
			searchCategory(tableView, newValue.getId());
		});
	}

	/*
	 * private void valueProductUpgradeDataBase() { upgradeProductBtn.setOnAction(_
	 * -> { DataBaseHelper.VeriModel productList =
	 * upgradeTableView.getSelectionModel().getSelectedItem(); if (productList !=
	 * null) { DataBaseHelper.upgradeProduct(upgradeBarkodTextBox.getText().trim().
	 * toLowerCase(), upgradeProductNameTextbox.getText().trim().toLowerCase(),
	 * upgradeproductquantityspinner.getValue(), upgradeChoiceBox.getValue(),
	 * Double.parseDouble(upgradeCostTextbox.getText()), productList.getId()); }
	 * tableViewUpgrade(upgradeTableView, "stok"); }); }
	 */
	private void searchCategory(TableView<DataBaseHelper.VeriModel> tableView, int aramaMetni) {
		try {
			if (aramaMetni != 1) {
				tableView.setItems(DataBaseHelper.kategoriFiltreleme(aramaMetni));

			} else if (aramaMetni == 1) {
				tableViewUpgrade(tableView, "ürünler");
			}
		} catch (Exception e) {
			System.out.println("search Category sorun var: " + e.getMessage());
		}
	}

	private void searchProduct(TableView<DataBaseHelper.VeriModel> tableView, String aramaMetni) {
		try {
			if (aramaMetni != null && !aramaMetni.trim().isEmpty()) {
				tableView.setItems(DataBaseHelper.stoklariAra(aramaMetni));
			} else {
				tableViewUpgrade(tableView, "ürünler");
			}
		} catch (Exception e) {
			System.out.println("search Product sorun var: " + e.getMessage());
		}
	}
}
