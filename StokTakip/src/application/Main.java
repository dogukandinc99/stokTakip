package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {

			DataBaseHelper.createTable("kategoriler", """
						    CREATE TABLE IF NOT EXISTS kategoriler (
					        id INTEGER PRIMARY KEY AUTOINCREMENT,
					        ad TEXT NOT NULL
					    );
					""");
			DataBaseHelper.createTable("stok", """
						    CREATE TABLE IF NOT EXISTS stok (
					        id INTEGER PRIMARY KEY AUTOINCREMENT,
					        barkod TEXT UNIQUE NOT NULL,
					 		urun_adi TEXT NOT NULL,
					 		urun_adet INTEGER NOT NULL,
					 		kategori TEXT NOT NULL,
					 		maliyet REAL NOT NULL
					    );
					""");
			Parent root = FXMLLoader.load(getClass().getResource("form1.fxml"));
			primaryStage.setTitle("STOK TAKİP");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			/*
			 * BorderPane root = new BorderPane(); Scene scene = new Scene(root,400,400);
			 * scene.getStylesheets().add(getClass().getResource("application.css").
			 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
