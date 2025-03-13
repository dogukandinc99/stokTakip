package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Form1Controller {

	@FXML
	private Button addStockBtn;

	@FXML
	private AnchorPane addStockForm;

	@FXML
	private TextField categoriTextBox;

	@FXML
	private Button homeBtn;

	@FXML
	private AnchorPane mainForm;

	@FXML
	private Button newcategoribtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private AnchorPane settingsForm;

	@FXML
	private TableView<?> settingstableview;

	@FXML
	private TableColumn<?, ?> settingstableviewcolumn1;

	@FXML
	private TableColumn<?, ?> settingstableviewcolumn2;

	@FXML
	private Button updateStockBtn;

	@FXML
	private AnchorPane updateStockForm;

	public void initialize() {
		settingstableviewcolumn1.setCellValueFactory(new PropertyValueFactory<>("id")); // id'yi bağladık
		settingstableviewcolumn2.setCellValueFactory(new PropertyValueFactory<>("ad")); // ad'ı bağladık
		settingstableview.setItems(DataBaseHelper.loadKategoriData()); // Verileri yükleme
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
	public void valueInsertDataBase(ActionEvent event) {
		String categoriString = categoriTextBox.getText().trim().toLowerCase();
		if (categoriString.isEmpty()) {
			System.out.println("Kategori kısmı boş girilemez.");
		} else {
			if (DataBaseHelper.kategoriVarMi(categoriString)) {
				System.out.println("Kategori zaten mevcut...");
			} else {
				DataBaseHelper.kategoriEkle(categoriString);
				System.out.println("Kategori eklendi...");
				initialize();
			}
		}
	}

}
