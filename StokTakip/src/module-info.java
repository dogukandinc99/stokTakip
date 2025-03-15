module StokTakip {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;

	opens application to javafx.graphics, javafx.fxml;
	exports application;
}
