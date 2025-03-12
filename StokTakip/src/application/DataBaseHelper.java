package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBaseHelper {
	private static final String DB_URL = "jdbc:sqlite:database/kategoriler.db";

	public static Connection connect() {
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
	public static void createTable() {
		String sql = """
				    CREATE TABLE IF NOT EXISTS kategoriler (
				        id INTEGER PRIMARY KEY AUTOINCREMENT,
				        ad TEXT NOT NULL
				    );
				""";
		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Kategoriler tablosu kontrol edildi/oluşturuldu.");
		} catch (SQLException e) {
			System.out.println("Tablo oluşturma hatası: " + e.getMessage());
		}
	}

	public static boolean kategoriVarMi(String ad) {
		String sql = "SELECT COUNT(*) FROM kategoriler WHERE ad = ?";

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

	public static void kategoriEkle(String ad) {
		String sql = "INSERT INTO kategoriler (ad) VALUES (?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, ad); // Kategori adını yerleştir
			pstmt.executeUpdate(); // Sorguyu çalıştır
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static class Categori {
		private final int id;
		private final String ad;

		public Categori(int id, String ad) {
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
	public static ObservableList loadKategoriData() {
		ObservableList<Categori> kategoriList = FXCollections.observableArrayList();

		// Veritabanından kategorileri çekme
		try (Connection conn = DataBaseHelper.connect()) {
			String sql = "SELECT id, ad FROM kategoriler"; // id'yi de ekledik
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int kategoriId = rs.getInt("id");
				String kategoriAd = rs.getString("ad");
				kategoriList.add(new Categori(kategoriId, kategoriAd)); // id ve ad'ı alıyoruz
			}
		} catch (SQLException e) {
			System.out.println("Kategori verilerini yüklerken hata oluştu: " + e.getMessage());
		}

		// TableView'ı verilerle doldur
		return kategoriList;
	}
}
