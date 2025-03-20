module StokTakip {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires transitive javafx.graphics;

	opens application to javafx.graphics, javafx.fxml, java.sql;

	exports application;
}
