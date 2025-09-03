package application;

import java.io.File;
import java.nio.channels.Pipe.SourceChannel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import javafx.scene.control.TableCell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.util.StringConverter;

public class Form1Controller {
	@FXML
	private StackPane bomDrawer;
	@FXML
	private StackPane bomMaterialDrawer;

	@FXML
	private AnchorPane addStockForm;
	@FXML
	private AnchorPane mainForm;
	@FXML
	private AnchorPane settingsForm;
	@FXML
	private AnchorPane updateStockForm;
	@FXML
	private AnchorPane drawerPane;
	@FXML
	private AnchorPane drawerMaterialPane;

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
	private Button editMaterialBtn;
	@FXML
	private Button drawerAddBtn;
	@FXML
	private Button drawerDeleteBtn;
	@FXML
	private Button drawerReadyBtn;
	@FXML
	private Button drawerMateralSaveBtn;
	@FXML
	private Button drawerMateralCancelBtn;

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
	private TextField drawerSearchTextbox;

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
	private ChoiceBox<VeriModel> drawerSearchChoiceBox;

	@FXML
	private Label materialsLabel;
	@FXML
	private Label materialsQuantityLabel;
	@FXML
	private Label errorMessage;

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
	private TableView<VeriModel> drawerTableView;
	@FXML
	private TableView<VeriModel> stokdrawerTableView;

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
	@FXML
	private TableColumn<VeriModel, Boolean> drawerTableViewColumn1;
	@FXML
	private TableColumn<VeriModel, Integer> drawerTableViewColumn2;
	@FXML
	private TableColumn<VeriModel, String> drawerTableViewColumn3;
	@FXML
	private TableColumn<VeriModel, Double> drawerTableViewColumn4;
	@FXML
	private TableColumn<VeriModel, String> drawerTableViewColumn5;
	@FXML
	private TableColumn<VeriModel, Boolean> stokDrawerTableViewColumn1;
	@FXML
	private TableColumn<VeriModel, Integer> stokDrawerTableViewColumn2;
	@FXML
	private TableColumn<VeriModel, String> stokDrawerTableViewColumn3;
	@FXML
	private TableColumn<VeriModel, String> stokDrawerTableViewColumn4;
	@FXML
	private TableColumn<VeriModel, Integer> stokDrawerTableViewColumn5;
	@FXML
	private TableColumn<VeriModel, String> stokDrawerTableViewColumn6;
	@FXML
	private TableColumn<VeriModel, Double> stokDrawerTableViewColumn7;
	@FXML
	private TableColumn<VeriModel, String> stokDrawerTableViewColumn8;
	@FXML
	private TableColumn<VeriModel, String> stokDrawerTableViewColumn9;

	@FXML
	private Region scrim;
	@FXML
	private Region materialScrim;

	@FXML
	private VBox drawerToolBox;

	ObservableList<String> unitList = FXCollections.observableArrayList();
	ObservableList<String> currencyList = FXCollections.observableArrayList();
	private final ObservableMap<Integer, BooleanProperty> drawerSelectMap = FXCollections.observableHashMap();
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

		drawerTableViewColumn1.setCellValueFactory(cd -> {
			VeriModel row = cd.getValue();
			int id = row.getUrunId();
			return drawerSelectMap.computeIfAbsent(id, _ -> new SimpleBooleanProperty(false));
		});
		drawerTableViewColumn1.setCellFactory(CheckBoxTableCell.forTableColumn(drawerTableViewColumn1));
		drawerTableViewColumn2.setCellValueFactory(new PropertyValueFactory<>("urunId"));
		drawerTableViewColumn3.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		drawerTableViewColumn4.setCellValueFactory(new PropertyValueFactory<>("miktar"));
		drawerTableViewColumn5.setCellValueFactory(new PropertyValueFactory<>("birim"));

		tableViewSettings(stokdrawerTableView, true);
		tableViewSettings(mainTableView, false);
		tableViewSettings(addProductTableView, false);
		addProductTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewSettings(upgradeTableView, false);

		setupSearchListener(mainSearchTextbox, mainTableView);
		setupSearchListener(mainChoiceBox, mainTableView);
		setupSearchListener(drawerSearchChoiceBox, stokdrawerTableView);
		setupSearchListener(drawerSearchTextbox, stokdrawerTableView);
		setupSearchListener(upgradeSearchTextbox, upgradeTableView);
		setupSearchListener(upgradeSearchChoiceBox, upgradeTableView);

		formManagedProporty();
		switchForm();

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
		bindUpdateAndDeleteButton();
		bindMaterialButton();
		bindChoiceBox();

		// Drawer aç: butona tıklayınca
		editMaterialBtn.setOnAction(_ -> openDrawerAndLoad());

		// "Hazır" şimdilik sadece kapanış yapsın (persist'i sonraki adımda ekleyeceğiz)
		drawerReadyBtn.setOnAction(_ -> {
			tableViewUpgrade(upgradeTableView, "ürünler");
			closeDrawer();
		});

		// (İstersen ESC ile de kapansın)
		bomDrawer.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
				closeDrawer();
				ev.consume();
			}
		});
		drawerDeleteBtn.setOnAction(_ -> onDrawerDelete());
		drawerAddBtn.setOnAction(_ -> onDrawerAdd());

		bomMaterialDrawer.managedProperty().bind(bomMaterialDrawer.visibleProperty());
		drawerMaterialPane.managedProperty().bind(drawerMaterialPane.visibleProperty());
		materialScrim.managedProperty().bind(bomMaterialDrawer.visibleProperty());

	}

	// sayfalar arası geçiş
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

	// sayfalar arası geçiş için oluşturuldu.
	private void showForm(AnchorPane visibleForm, TableView<VeriModel> tableView, String tabloAdi) {

		mainForm.setVisible(false);
		addStockForm.setVisible(false);
		updateStockForm.setVisible(false);
		settingsForm.setVisible(false);

		visibleForm.setVisible(true);

		tableViewUpgrade(tableView, tabloAdi);
	}

	private void formManagedProporty() {
		mainForm.managedProperty().bind(mainForm.visibleProperty());
		addStockForm.managedProperty().bind(addStockForm.visibleProperty());
		updateStockForm.managedProperty().bind(updateStockForm.visibleProperty());
		settingsForm.managedProperty().bind(settingsForm.visibleProperty());
	}

	// kategorileri choicebxlara koymak için oluşturuldu.
	private void ChoiceBoxs() {
		fillChoiceBox(mainChoiceBox);
		fillChoiceBox(categorychoicebox);
		fillChoiceBox(upgradeSearchChoiceBox);
		fillChoiceBox(upgradeChoiceBox);
		fillChoiceBox(drawerSearchChoiceBox);
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
		ValidationUtil.applyDecimalTextField(upgradeCostTextbox, 6, 12, false);
	}

	private void bindSaveButton() {
		var invalidForm = Bindings.createBooleanBinding(() -> {
			boolean barkod = barkodtextbox.getText() == null || barkodtextbox.getText().trim().isEmpty();
			boolean product = producttextbox.getText() == null || producttextbox.getText().trim().isEmpty();
			boolean cost = costtextbox.getText() == null || costtextbox.getText().trim().isEmpty();
			errorMessage.setText("Ürün ekleyebilmeniz için boş alanları doldurmanız gerekmektedi...");
			boolean categoriAll = categorychoicebox.getValue().getKategori().toLowerCase().equals("hepsi");
			Object v = categorychoicebox.getValue().getKategori();
			String s = (v == null) ? "" : v.toString().toLowerCase();
			boolean isUrunler = s.equals("ürünler");
			boolean hasSel = addProductTableView.getSelectionModel().getSelectedItem() != null;
			boolean sonuc = isUrunler && !hasSel;
			if (sonuc == true) {
				errorMessage.setText(
						"Ürün ekleyebilmeniz için tablodan HAM MADDE ve/veya AMBALAJ seçmeniz gerekmektedir...");
			} else if (!barkod && !product && !cost && !sonuc && !categoriAll) {
				errorMessage.setText("Herhangi Bir Problem Göremedim...");
			} else if (categoriAll) {
				errorMessage.setText("Ürün ekleyebilmeniz için kategori belirlemeniz gerekmektedir...");
			}
			return barkod || product || cost || sonuc || categoriAll;
		}, barkodtextbox.textProperty(), producttextbox.textProperty(), costtextbox.textProperty(),
				categorychoicebox.valueProperty(), addProductTableView.getSelectionModel().selectedItemProperty());

		addProductBtn.disableProperty().unbind();
		// Yeniden bağlamadan önce unbind et (aynı metoda tekrar girilirse hata olmasın)
		addProductBtn.disableProperty().bind(invalidForm);

		var invalidForm2 = Bindings.createBooleanBinding(() -> {
			boolean kategoriName = categoriTextBox.getText() == null || categoriTextBox.getText().trim().isEmpty();
			return kategoriName;
		}, categoriTextBox.textProperty());

		newcategoribtn.disableProperty().unbind();
		// Yeniden bağlamadan önce unbind et (aynı metoda tekrar girilirse hata olmasın)
		newcategoribtn.disableProperty().bind(invalidForm2);
	}

	private void bindUpdateAndDeleteButton() {
		var invalidForm = Bindings.createBooleanBinding(() -> {
			boolean hasSel = upgradeTableView.getSelectionModel().getSelectedItem() == null;
			boolean barkod = upgradeBarkodTextBox.getText() == null || upgradeBarkodTextBox.getText().trim().isEmpty();
			boolean product = upgradeProductNameTextbox.getText() == null
					|| upgradeProductNameTextbox.getText().trim().isEmpty();

			boolean categoriAll = upgradeChoiceBox.getValue().getKategori().toLowerCase().equals("hepsi");
			return barkod || product || hasSel || categoriAll;
		}, upgradeTableView.getSelectionModel().selectedItemProperty(), upgradeBarkodTextBox.textProperty(),
				upgradeProductNameTextbox.textProperty(), upgradeChoiceBox.valueProperty());

		upgradeProductBtn.disableProperty().unbind();
		deleteProductBtn.disableProperty().unbind();
		// Yeniden bağlamadan önce unbind et (aynı metoda tekrar girilirse hata olmasın)
		upgradeProductBtn.disableProperty().bind(invalidForm);
		deleteProductBtn.disableProperty().bind(invalidForm);

		var invalidForm2 = Bindings.createBooleanBinding(() -> {

			boolean kategori = settingstableview.getSelectionModel().getSelectedItem() == null;
			return kategori;
		}, settingstableview.getSelectionModel().selectedItemProperty());

		deletecategoribtn.disableProperty().unbind();
		// Yeniden bağlamadan önce unbind et (aynı metoda tekrar girilirse hata olmasın)
		deletecategoribtn.disableProperty().bind(invalidForm2);
	}

	private void bindChoiceBox() {
		costtextbox.disableProperty().unbind();
		costtextbox.editableProperty().unbind();
		// "Ürünler" seçili mi?
		var isUrunler = Bindings.createBooleanBinding(() -> {
			VeriModel vm = categorychoicebox.getValue();
			return vm != null && "ürünler".equalsIgnoreCase(vm.getKategori());
		}, categorychoicebox.valueProperty());

		// A) Tamamen devre dışı bırak (gri)
		costtextbox.disableProperty().bind(isUrunler);
	}

	private void bindMaterialButton() {
		var showEditBtn = Bindings.createBooleanBinding(() -> {
			VeriModel sel = upgradeTableView.getSelectionModel().getSelectedItem();
			if (sel == null)
				return false;
			String kat = sel.getKategori();
			return kat != null && kat.trim().toLowerCase().equals("ürünler");
		}, upgradeTableView.getSelectionModel().selectedItemProperty());

		// gizliyken yer kaplamasın
		editMaterialBtn.managedProperty().bind(editMaterialBtn.visibleProperty());
		editMaterialBtn.visibleProperty().bind(showEditBtn);
	}

	private final ObservableMap<Integer, BooleanProperty> materialSelectMap = FXCollections.observableHashMap();

	// tablo ayarları için oluşturuldu.
	private void tableViewSettings(TableView<VeriModel> tableView, boolean withCheckbox) {
		int indexshift = 0;
		ObservableList<TableColumn<VeriModel, ?>> kolonlar = tableView.getColumns();
		if (withCheckbox) {
			TableColumn<VeriModel, Boolean> checkboxCol = (TableColumn<VeriModel, Boolean>) kolonlar.get(indexshift);
			checkboxCol.setCellValueFactory(cd -> {
				VeriModel row = cd.getValue();
				int id = row.getUrunId();
				return materialSelectMap.computeIfAbsent(id, k -> new SimpleBooleanProperty(false));
			});
			checkboxCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxCol));
			indexshift++;
		}

		kolonlar.get(indexshift).setCellValueFactory(new PropertyValueFactory<>("urunId"));
		kolonlar.get(indexshift + 1).setCellValueFactory(new PropertyValueFactory<>("barkod"));
		kolonlar.get(indexshift + 2).setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		kolonlar.get(indexshift + 3).setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
		kolonlar.get(indexshift + 4).setCellValueFactory(new PropertyValueFactory<>("birim"));
		TableColumn<VeriModel, String> kategoriCol = (TableColumn<VeriModel, String>) kolonlar.get(indexshift + 5);
		kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
		kategoriCol.setCellFactory(_ -> new TableCell<VeriModel, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					// Türkçe büyük harf dönüşümü için TR locale kullan
					setText(item.toUpperCase());
				}
			}
		});
		TableColumn<VeriModel, Double> maliyetKolon = (TableColumn<VeriModel, Double>) kolonlar.get(indexshift + 6);
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
		TableColumn<VeriModel, String> paraBirimiCol = (TableColumn<VeriModel, String>) kolonlar.get(indexshift + 7);
		paraBirimiCol.setCellValueFactory(new PropertyValueFactory<>("paraBirimi"));
		paraBirimiCol.setCellFactory(_ -> new TableCell<VeriModel, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					// Türkçe büyük harf dönüşümü için TR locale kullan
					setText(item.toUpperCase());
				}
			}
		});

		tableViewUpgrade(tableView, "ürünler");
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
			try {
				services.kategoriEkle(categoriString);
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
			try {
				services.kategoriSil(selectCategory.getKategoriId());
				tableViewUpgrade(settingstableview, "kategoriler");
				ChoiceBoxs();
			} catch (Exception e) {
				System.out.println("valueCategoryDeleteDataBase sorun var: " + e.getMessage());
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

			if (!costtextbox.getText().isEmpty()) {
				maliyet = Double.parseDouble(costtextbox.getText());
			}
			if (!category.getKategori().equals("ürünler")) {
				services.urunEkle(barkod, ürünAdi, ürünAdet, birim, category.getKategori(), maliyet, paraBirimi);
			} else if (category.getKategori().equals("ürünler")) {
				ObservableList<VeriModel> tableSelectedList = FXCollections
						.observableArrayList(addProductTableView.getSelectionModel().getSelectedItems());

				boolean kontrol = false;
				for (int i = 0; i < tableSelectedList.size(); i++) {
					if (!tableSelectedList.get(i).getKategori().toLowerCase().equals("ambalajlar")
							&& !tableSelectedList.get(i).getKategori().toLowerCase().equals("ham maddeler")) {
						kontrol = true;
					}
				}
				try {
					if (!kontrol) {
						services.urunEkle(barkod, ürünAdi, ürünAdet, birim, category.getKategori(), 0.0, paraBirimi);
						openQtyDrawer(tableSelectedList);
					} else {
						information("Bilgi", null,
								"Ürünü ekleyebilmek için tablodan Ham Madde ve/veya Ambalaj seçimi yapmanız gerekmektedir...",
								AlertType.INFORMATION);
					}
				} catch (Exception e) {
					System.out.println("Beklenmedik bir hata ile karşılaştık.\nHata: " + e.getMessage());
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
				services.urunGuncelle(ürün.get(0).getUrunId(), ürün.get(0).getBarkod(), ürün.get(0).getUrunAdi(),
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

	// ürün silmek için oluşturuldu.
	private void valueProductDeleteDataBase() {
		deleteProductBtn.setOnAction(_ -> {
			ObservableList<VeriModel> selectProduct = FXCollections
					.observableArrayList(upgradeTableView.getSelectionModel().getSelectedItem());

			if (upgradeTableView.getSelectionModel().getSelectedItem() == null) {
				System.out.println("Lütfen bir stok seçin.");
				return;
			} else {
				if (selectProduct.get(0).getKategori().toLowerCase().equals("ürünler")) {
					ObservableList<VeriModel> bilesenList = services
							.icerdigiBilesenler(selectProduct.get(0).getUrunId());
					for (VeriModel m : bilesenList) {
						services.bilesenSil(selectProduct.get(0).getUrunId(), m.getUrunId());
					}
				} else {
					ObservableList<VeriModel> icerdigiUrunlerListesi = services
							.icerdigiUrunler(selectProduct.get(0).getUrunId());
					if (icerdigiUrunlerListesi != null) {
						for (VeriModel m : icerdigiUrunlerListesi) {
							services.bilesenSil(m.getUrunId(), selectProduct.get(0).getUrunId());
							productMaterialsCost(m.getUrunId());
						}
					}
				}
				services.urunSil(selectProduct.get(0).getUrunId());
			}
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

			if (productList.getKategori().toLowerCase().equals("ürünler")) {
				services.urunGuncelle(productList.getUrunId(), upgradeBarkodTextBox.getText().trim().toLowerCase(),
						upgradeProductNameTextbox.getText().trim().toLowerCase(),
						upgradeproductquantityspinner.getValue(), upgradeUnitChoiceBox.getValue(),
						upgradeChoiceBox.getValue().getKategori(), Double.parseDouble(upgradeCostTextbox.getText()));
			} else if (productList.getKategori().toLowerCase().equals("ham maddeler")
					|| productList.getKategori().toLowerCase().equals("ambalajlar")) {
				if (productList.getMaliyet() != Double.parseDouble(upgradeCostTextbox.getText())) {
					services.maliyetGuncelle(productList.getUrunId(), Double.parseDouble(upgradeCostTextbox.getText()));
				}
				services.urunGuncelle(productList.getUrunId(), upgradeBarkodTextBox.getText().trim().toLowerCase(),
						upgradeProductNameTextbox.getText().trim().toLowerCase(),
						upgradeproductquantityspinner.getValue(), upgradeUnitChoiceBox.getValue(),
						upgradeChoiceBox.getValue().getKategori(), Double.parseDouble(upgradeCostTextbox.getText()));
			} else {
				services.urunGuncelle(productList.getUrunId(), upgradeBarkodTextBox.getText().trim().toLowerCase(),
						upgradeProductNameTextbox.getText().trim().toLowerCase(),
						upgradeproductquantityspinner.getValue(), upgradeUnitChoiceBox.getValue(),
						upgradeChoiceBox.getValue().getKategori(), Double.parseDouble(upgradeCostTextbox.getText()));
			}

			tableViewUpgrade(upgradeTableView, "ürünler");
		});
	}

	private void openDrawerAndLoad() {
		VeriModel sel = upgradeTableView.getSelectionModel().getSelectedItem();
		if (sel == null)
			return;

		bomDrawer.setVisible(true);
		bomDrawer.setManaged(true);
		drawerPane.setTranslateX(drawerPane.getWidth()); // sağda başlasın
		var t = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(220), drawerPane);
		t.setToX(0);
		t.play();
		drawerTableView.setItems(services.icerdigiBilesenler(sel.getUrunId()));
		drawerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// klavye odak
		bomDrawer.requestFocus();
	}

	private void productMaterialsCost(int ürün_id) {
		ObservableList<VeriModel> bilesenListesi = services.icerdigiBilesenler(ürün_id);
		System.out.println(bilesenListesi.get(0).getUrunId());
		Double bilesenToplamMaliyet = 0.0;
		for (int i = 0; i < bilesenListesi.size(); i++) {

			bilesenToplamMaliyet += bilesenListesi.get(i).getMiktar() * bilesenListesi.get(i).getMaliyet();

			System.out.println(bilesenListesi.get(i).getMaliyet());
		}
		services.urunMaliyetGuncelle(ürün_id, bilesenToplamMaliyet);

	}

	@FXML
	private void onDrawerScrimClick() {
		closeDrawer();
	}

	private void closeDrawer() {
		var t = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(200), drawerPane);
		t.setToX(drawerPane.getWidth());
		t.setOnFinished(_ -> {
			bomDrawer.setVisible(false);
			bomDrawer.setManaged(false);
		});
		t.play();
	}

	private void onDrawerDelete() {
		// Controller alanı

		drawerTableView.setEditable(true); // checkbox için gerekli

		drawerTableViewColumn1.setCellValueFactory(cd -> {
			VeriModel row = cd.getValue();
			return drawerSelectMap.computeIfAbsent(row.getUrunId(), _ -> new SimpleBooleanProperty(false));
		});
		drawerTableViewColumn1.setCellFactory(CheckBoxTableCell.forTableColumn(drawerTableViewColumn1));
		drawerTableViewColumn1.setEditable(true);
		List<Integer> checkedIds = drawerTableView.getItems().stream()
				.filter(vm -> drawerSelectMap.getOrDefault(vm.getUrunId(), new SimpleBooleanProperty(false)).get())
				.map(VeriModel::getUrunId).toList();

		VeriModel sel = upgradeTableView.getSelectionModel().getSelectedItem();
		for (int i = 0; i < checkedIds.size(); i++) {
			services.bilesenSil(sel.getUrunId(), checkedIds.get(i));
		}
		productMaterialsCost(sel.getUrunId());
		drawerTableView.setItems(services.icerdigiBilesenler(sel.getUrunId()));
	}

	private void onDrawerAdd() {
		// Controller alanı

		stokdrawerTableView.setEditable(true); // checkbox için gerekli

		stokDrawerTableViewColumn1.setCellValueFactory(cd -> {
			VeriModel row = cd.getValue();
			return materialSelectMap.computeIfAbsent(row.getUrunId(), _ -> new SimpleBooleanProperty(false));
		});
		stokDrawerTableViewColumn1.setCellFactory(CheckBoxTableCell.forTableColumn(stokDrawerTableViewColumn1));
		stokDrawerTableViewColumn1.setEditable(true);
		BooleanProperty FALSE = new SimpleBooleanProperty(false);
		ObservableList<VeriModel> selectedMaterials = stokdrawerTableView.getItems().stream()
				.filter(vm -> materialSelectMap.getOrDefault(vm.getUrunId(), FALSE).get())
				.collect(java.util.stream.Collectors
						.toCollection(javafx.collections.FXCollections::observableArrayList));

		openQtyDrawer(selectedMaterials);
	}

	private final java.util.Map<Integer, Double> qtySpinners = new java.util.HashMap<>();

	private void openQtyDrawer(ObservableList<VeriModel> selectedMaterials) {
		// önce temizle
		drawerToolBox.getChildren().clear();
		qtySpinners.clear();
		for (VeriModel m : selectedMaterials) {
			Label name = new Label(
					m.getUrunAdi() + " (Birim: " + (m.getBirim() == null ? "" : m.getBirim().toUpperCase()) + ")");
			name.setStyle("-fx-font-size: 13px;");

			Spinner<Double> sp = new Spinner<>();
			sp.setPrefWidth(100);
			sp.setEditable(true);

			// Kendi util’inle düzgün giriş (ondalıklı, negatif yok, scale=3 ör.)
			ValidationUtil.applyDecimalSpinner(sp, /* scale */3, /* maxLen */12, /* allowNegative */false);
			// Varsayılan bir değer (istersen 0.0 da koyabilirsin)
			sp.getValueFactory().setValue(1.0);

			sp.getValueFactory().valueProperty().addListener((_, _, newV) -> {
				qtySpinners.put(m.getUrunId(), newV == null ? 0.0 : newV);
			});

			// Kullanıcı kutuya yazıp focus kaybedince değeri commit et
			sp.focusedProperty().addListener((obs, was, isNow) -> {
				if (!isNow) {
					var vf = sp.getValueFactory();
					var txt = sp.getEditor().getText();
					try {
						vf.setValue(vf.getConverter().fromString(txt));
					} catch (Exception ignore) {
					}
				}
			});

			HBox row = new HBox(10);
			Region spacerLeft = new Region();
			HBox.setHgrow(spacerLeft, Priority.ALWAYS);
			row.getChildren().addAll(sp, spacerLeft, name);

			drawerToolBox.getChildren().add(row);
		}
		System.out.println("buradayım");

		drawerMateralSaveBtn.setOnAction(_ -> {
			for (int i = 0; i < selectedMaterials.size(); i++) {
				services.icindekileriEkle(services.sonId("ürünler") - 1, selectedMaterials.get(i).getUrunId(),
						qtySpinners.get(selectedMaterials.get(i).getUrunId()), selectedMaterials.get(i).getBirim());
				productMaterialsCost(services.sonId("ürünler") - 1);
				closeQtyDrawer();
			}
		});

		bomMaterialDrawer.setVisible(true);
		var tt = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(180), drawerMaterialPane);
		tt.setToX(0);
		tt.play();
	}

	@FXML
	private void onQtyScrimClick() {
		closeQtyDrawer();
	}

	@FXML
	private void onQtyCancel() {
		closeQtyDrawer();
	}

	private void closeQtyDrawer() {
		var tt = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(180), drawerMaterialPane);
		tt.setToX(drawerMaterialPane.getPrefWidth());
		tt.setOnFinished(_ -> bomMaterialDrawer.setVisible(false));
		tt.play();
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
					StringBuilder bilesenler = new StringBuilder();
					ObservableList<VeriModel> bilesenListesi = services.icerdigiBilesenler(urun.getUrunId());
					if (bilesenListesi.isEmpty()) {
						bilesenler.append("İÇİNDEKİLER\\nBulunamadı...");
					} else {
						for (int i = 0; i < bilesenListesi.size(); i++) {
							bilesenler.append(bilesenListesi.get(i).getUrunAdi().toUpperCase()).append(": ")
									.append(bilesenListesi.get(i).getMiktar()).append(" ")
									.append(bilesenListesi.get(i).getBirim().toUpperCase())
									.append(" Maliyet(1 Birim): ").append(bilesenListesi.get(i).getMaliyet())
									.append("\n");
						}
					}
					Tooltip tooltip = new Tooltip(bilesenler.toString());
					Tooltip.install(row, tooltip);
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
