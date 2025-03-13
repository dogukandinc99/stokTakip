package application;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBaseHelper {
	static String dbName;

	public static Connection connect() {
		// String DB_URL = "jdbc:sqlite:database/kategoriler.db";
		// Programın çalıştığı dizini alıyoruz
		String workingDir = System.getProperty("user.dir");

		// Veritabanının koyulacağı "database" klasörü
		String dbFolderPath = workingDir + "/database"; // Programın çalıştığı dizinde "database" klasörünü oluşturacak

		// "database" klasörünü kontrol et, yoksa oluştur
		File folder = new File(dbFolderPath);
		if (!folder.exists()) {
			folder.mkdirs(); // Klasör yoksa oluştur
		}
		// "kategoriler.db" dosyasının yolu
		String DB_URL = "jdbc:sqlite:" + dbFolderPath + "/" + dbName + ".db"; // Veritabanı dosyasının tam yolu

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("SQLite bağlantısı başarılı!");
		} catch (SQLException e) {
			System.out.println("Bağlantı hatası: " + e.getMessage());
		}
		return conn;
	}

	// Kategoriler tablosunu oluşturma
	public static void createTable(String database, String sql) {
		dbName = database;
		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Kategoriler tablosu kontrol edildi/oluşturuldu.");
		} catch (SQLException e) {
			System.out.println("Tablo oluşturma hatası: " + e.getMessage());
		}
	}

	public static boolean kategoriVarMi(String database, String ad) {
		String sql = "SELECT COUNT(*) FROM kategoriler WHERE ad = ?";
		dbName = database;
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, ad);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Eğer 0'dan büyükse kategori zaten var
			}
		} catch (SQLException e) {
			System.out.println("Kategori kontrol hatası: " + e.getMessage());
		}
		return false; // Hata durumunda yeni eklenebilir kabul eder
	}

	public static void kategoriEkle(String database, String ad) {
		String sql = "INSERT INTO kategoriler (ad) VALUES (?)";
		dbName = database;
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, ad); // Kategori adını yerleştir
			pstmt.executeUpdate(); // Sorguyu çalıştır
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static class Category {
		private int id;
		private String ad;

		public Category(int id, String ad) {
			this.id = id;
			this.ad = ad;
		}

		public int getId() {
			return id;
		}

		public String getAd() {
			return ad;
		}
	}

	// Veritabanından verileri alıp TableView'a yerleştirme
	public static ObservableList loadKategoriData(String database) {
		ObservableList<Category> kategoriList = FXCollections.observableArrayList();
		dbName = database;
		// Veritabanından kategorileri çekme
		try (Connection conn = DataBaseHelper.connect()) {
			String sql = "SELECT id, ad FROM kategoriler"; // id'yi de ekledik
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int kategoriId = rs.getInt("id");
				String kategoriAd = rs.getString("ad");
				kategoriList.add(new Category(kategoriId, kategoriAd)); // id ve ad'ı alıyoruz
			}
		} catch (SQLException e) {
			System.out.println("Kategori verilerini yüklerken hata oluştu: " + e.getMessage());
		}

		// TableView'ı verilerle doldur
		return kategoriList;
	}

	public static void categorySil(String database, int id) {
		String sql = "DELETE FROM kategoriler WHERE id = ?";
		dbName = database;
		int kategoriid = id;
		try (Connection conn = DataBaseHelper.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, kategoriid);
			int silinenSatir = pstmt.executeUpdate();
			if (silinenSatir > 0) {
				System.out.println("Kategori başarıyla silindi.");
			} else {
				System.out.println("Kategori bulunamadı.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
