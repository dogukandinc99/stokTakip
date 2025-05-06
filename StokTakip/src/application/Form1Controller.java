package application;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.scene.control.TableCell;
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

	ObservableList<String> unitList = FXCollections.observableArrayList();
	Services services = new Services();

	public void initialize() {

		SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-999999999,
				999999999, 1);
		productquantityspinner.setValueFactory(valueFactory);
		upgradeproductquantityspinner.setValueFactory(valueFactory);
		mainQuantityspinner.setValueFactory(valueFactory);

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

		ChoiceBoxs();

		services.tablolariOlustur();

		settingstableviewcolumn1.setCellValueFactory(new PropertyValueFactory<>("kategoriId"));
		settingstableviewcolumn2.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		tableViewUpgrade(settingstableview, "kategoriler");

		/*
		 * addProductTableViewColumn1.setCellValueFactory(new
		 * PropertyValueFactory<>("urunId"));
		 * addProductTableViewColumn2.setCellValueFactory(new
		 * PropertyValueFactory<>("barkod"));
		 * addProductTableViewColumn3.setCellValueFactory(new
		 * PropertyValueFactory<>("urunAdi"));
		 * addProductTableViewColumn4.setCellValueFactory(new
		 * PropertyValueFactory<>("urunAdet"));
		 * addProductTableViewColumn5.setCellValueFactory(new
		 * PropertyValueFactory<>("kategori"));
		 * addProductTableViewColumn6.setCellValueFactory(new
		 * PropertyValueFactory<>("maliyet"));
		 * addProductTableViewColumn6.setCellFactory(col -> new TableCell<VeriModel,
		 * Double>() {
		 * 
		 * @Override protected void updateItem(Double item, boolean empty) {
		 * super.updateItem(item, empty); if (empty || item == null) { setText(null); }
		 * else { // Burada maliyeti iki ondalıklı olarak gösterebilirsiniz
		 * DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		 * DecimalFormat decimalFormat = new DecimalFormat("#.##############", symbols);
		 * setText(decimalFormat.format(item)); } } });
		 * addProductTableViewColumn7.setCellValueFactory(new
		 * PropertyValueFactory<>("birim"));
		 * addProductTableView.getSelectionModel().setSelectionMode(SelectionMode.
		 * MULTIPLE); tableViewUpgrade(addProductTableView, "ürünler");
		 * 
		 * mainTableViewColumn1.setCellValueFactory(new
		 * PropertyValueFactory<>("urunId"));
		 * mainTableViewColumn2.setCellValueFactory(new
		 * PropertyValueFactory<>("barkod"));
		 * mainTableViewColumn3.setCellValueFactory(new
		 * PropertyValueFactory<>("urunAdi"));
		 * mainTableViewColumn4.setCellValueFactory(new
		 * PropertyValueFactory<>("urunAdet"));
		 * mainTableViewColumn5.setCellValueFactory(new
		 * PropertyValueFactory<>("kategori"));
		 * mainTableViewColumn6.setCellValueFactory(new
		 * PropertyValueFactory<>("maliyet")); mainTableViewColumn6.setCellFactory(col
		 * -> new TableCell<VeriModel, Double>() {
		 * 
		 * @Override protected void updateItem(Double item, boolean empty) {
		 * super.updateItem(item, empty); if (empty || item == null) { setText(null); }
		 * else { // Burada maliyeti iki ondalıklı olarak gösterebilirsiniz
		 * DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		 * DecimalFormat decimalFormat = new DecimalFormat("#.##############", symbols);
		 * // Ondalık kısmı // dinamik // şekilde göster
		 * setText(decimalFormat.format(item)); } } });
		 * mainTableViewColumn7.setCellValueFactory(new
		 * PropertyValueFactory<>("birim")); tableViewUpgrade(mainTableView, "ürünler");
		 * 
		 * upgradeTableViewColumn1.setCellValueFactory(new
		 * PropertyValueFactory<>("urunId"));
		 * upgradeTableViewColumn2.setCellValueFactory(new
		 * PropertyValueFactory<>("barkod"));
		 * upgradeTableViewColumn3.setCellValueFactory(new
		 * PropertyValueFactory<>("urunAdi"));
		 * upgradeTableViewColumn4.setCellValueFactory(new
		 * PropertyValueFactory<>("urunAdet"));
		 * upgradeTableViewColumn5.setCellValueFactory(new
		 * PropertyValueFactory<>("kategori"));
		 * upgradeTableViewColumn6.setCellValueFactory(new
		 * PropertyValueFactory<>("maliyet")); // Maliyet kolonunu formatlamak için bir
		 * cellFactory ekleyin upgradeTableViewColumn6.setCellFactory(col -> new
		 * TableCell<VeriModel, Double>() {
		 * 
		 * @Override protected void updateItem(Double item, boolean empty) {
		 * super.updateItem(item, empty); if (empty || item == null) { setText(null); }
		 * else { // Burada maliyeti iki ondalıklı olarak gösterebilirsiniz
		 * DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		 * DecimalFormat decimalFormat = new DecimalFormat("#.##############", symbols);
		 * setText(decimalFormat.format(item)); } } });
		 * upgradeTableViewColumn7.setCellValueFactory(new
		 * PropertyValueFactory<>("birim")); tableViewUpgrade(upgradeTableView,
		 * "ürünler");
		 */
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
		valueProductTakeOutDataBase();
		exportToExcel();

		setTooltipForTableview(mainTableView);
		setTooltipForTableview(addProductTableView);
		setTooltipForTableview(upgradeTableView);
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

	private void tableViewSettings(TableView<VeriModel> tableView) {
		ObservableList<TableColumn<VeriModel, ?>> kolonlar = tableView.getColumns();
		kolonlar.get(0).setCellValueFactory(new PropertyValueFactory<>("urunId"));
		kolonlar.get(1).setCellValueFactory(new PropertyValueFactory<>("barkod"));
		kolonlar.get(2).setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		kolonlar.get(3).setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		kolonlar.get(4).setCellValueFactory(new PropertyValueFactory<>("birim"));
		kolonlar.get(5).setCellValueFactory(new PropertyValueFactory<>("kategori"));
		TableColumn<VeriModel, Double> maliyetKolon = (TableColumn<VeriModel, Double>) kolonlar.get(6);
		maliyetKolon.setCellValueFactory(new PropertyValueFactory<>("maliyet"));
		maliyetKolon.setCellFactory(col -> new TableCell<VeriModel, Double>() {

			@Override
			protected void updateItem(Double item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else { // Burada maliyeti iki ondalıklı olarak gösterebilirsiniz
					DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
					DecimalFormat decimalFormat = new DecimalFormat("#.##############", symbols);
					setText(decimalFormat.format(item));
				}
			}
		});

		tableViewUpgrade(tableView, "ürünler");
	}

	private void showForm(AnchorPane visibleForm, TableView<VeriModel> tableView, String tabloAdi) {
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

	private void fillChoiceBox(ChoiceBox<VeriModel> choiceBox) {
		ObservableList<VeriModel> kategoriList = services.ürünListele("kategoriler");

		choiceBox.setConverter(new StringConverter<VeriModel>() {
			@Override
			public String toString(VeriModel kategori) {
				return (kategori != null && kategori.getKategori() != null) ? kategori.getKategori() : "";
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

	private void tableViewUpgrade(TableView<VeriModel> tableView, String table) {
		try {
			tableView.setItems(services.ürünListele(table));
			tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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

	private void valueProductInsertDataBase() {
		addProductBtn.setOnAction(_ -> {
			String barkod = barkodtextbox.getText().trim().toLowerCase();
			String ürünAdi = producttextbox.getText().trim().toLowerCase();
			Double ürünAdet = productquantityspinner.getValue();
			String birim = unitChoiceBox.getValue();
			VeriModel category = categorychoicebox.getValue();
			Double maliyet = 0.0;

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
					services.ürünEkle(barkod, ürünAdi, ürünAdet, birim, category.getKategori(), maliyet);
				}
			} else if (category.getKategori().equals("ürünler")) {
				if (barkod.isEmpty() && ürünAdi.isEmpty() && ürünAdet == 0 && maliyet == 0) {
					System.out.println("Bazı alanlar boş...");
				} else {
					ObservableList<VeriModel> tableSelectedList = addProductTableView.getSelectionModel()
							.getSelectedItems();
					if (tableSelectedList.size() < 1) {
						System.out.println("Tablodan Ham Madde Seçimi Yapmanız Gerekmektedir...");
					} else {
						try {
							valueProductMaterialsInsertDataBase(barkod, ürünAdi, ürünAdet, birim,
									category.getKategori(), tableSelectedList);
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
				information("Bilgi", null, "Stok adeti değiştirebilmek için listeden bir ürün seçmeniz gerekmektedi...",
						AlertType.INFORMATION);
				return;
			}
		});

	}

	private void valueProductTakeOutDataBase() {
		mainStokCikarBtn.setOnAction(_ -> {
			ObservableList<VeriModel> ürün = mainTableView.getSelectionModel().getSelectedItems();
			if (!ürün.isEmpty()) {
				double toplam = ürün.get(0).getUrunAdet();
				toplam -= mainQuantityspinner.getValue();
				services.ürünGüncelle(ürün.get(0).getUrunId(), ürün.get(0).getBarkod(), ürün.get(0).getUrunAdi(),
						toplam, ürün.get(0).getBirim(), ürün.get(0).getKategori(), ürün.get(0).getMaliyet());
				tableViewUpgrade(mainTableView, "ürünler");
				System.out.println("başarılı");
			} else {
				information("Bilgi", null, "Stok adeti değiştirebilmek için listeden bir ürün seçmeniz gerekmektedi...",
						AlertType.INFORMATION);
				return;
			}
		});

	}

	void valueProductMaterialsInsertDataBase(String barkod, String urunAdi, Double urunAdet, String birim,
			String kategori, ObservableList<VeriModel> selectedMaterials) {

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
					services.ürünEkle(barkod, urunAdi, urunAdet, birim, kategori, maliyetToplam);
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
				DecimalFormat decimalFormat = new DecimalFormat("#.##############", symbols); // Ondalık kısmı dinamik
				upgradeCostTextbox.setText(decimalFormat.format(yeniSecim.getMaliyet()));
			}
		});
		mainTableView.getSelectionModel().selectedItemProperty().addListener((_, _, yeniSecim) -> {
			if (yeniSecim != null) {
				mainTextbox.setText(yeniSecim.getUrunAdi());
			}
		});
	}

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

	private void setupSearchListener(TextField textField, TableView<VeriModel> tableView) {
		textField.textProperty().addListener((_, _, newValue) -> {
			if (!newValue.isEmpty()) {
				searchProduct(tableView, newValue);
			}
		});
	}

	private void setupSearchListener(ChoiceBox<VeriModel> choiceBox, TableView<VeriModel> tableView) {
		choiceBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
			if (newValue != null) {
				searchCategory(tableView, newValue.getKategori());
			}
		});
	}

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

	private void information(String title, String header, String mesaj, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(mesaj);
		alert.showAndWait();
	}
}
