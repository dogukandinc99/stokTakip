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
	public static void createTable(String sql) {

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println("Tablo oluşturma hatası: " + e.getMessage());
		}
	}

	public static boolean tabloVarMi(String tableName) {
		String sql = "SELECT COUNT(*) FROM sqlite_master WHERE type= 'table' AND name= ? ";
		dbName = tableName;
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, tableName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Eğer 0'dan büyükse kategori zaten var
			}
		} catch (SQLException e) {
			System.out.println("Kategori kontrol hatası: " + e.getMessage());
		}
		return false; // Hata durumunda yeni eklenebilir kabul eder
	}

	public static void kategoriEkle(String tabloName, String ad) {
		String sql = "INSERT INTO kategoriler (ad) VALUES (?)";
		dbName = tabloName;
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, ad); // Kategori adını yerleştir
			pstmt.executeUpdate(); // Sorguyu çalıştır
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static void addProduct(String database, String barkod, String productname, int productquantity,
			String category, Double cost) {
		String sql = "INSERT INTO stok (barkod,urun_adi,urun_adet,kategori,maliyet) VALUES (?,?,?,?,?)";
		dbName = database;
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, barkod);
			pstmt.setString(2, productname);
			pstmt.setInt(3, productquantity);
			pstmt.setString(4, category);
			pstmt.setDouble(5, cost);
			pstmt.executeUpdate(); // Sorguyu çalıştır
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static boolean degerVarMi(String tableName, String sütunAdi, String kategoriAdi) {
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + sütunAdi + " = ?";
		dbName = tableName;

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, kategoriAdi);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0; // Eğer 0'dan büyükse kategori zaten var
			}
		} catch (SQLException e) {
			System.out.println("Kategori kontrol hatası: " + e.getMessage());
		}
		return false; // Hata durumunda yeni eklenebilir kabul eder
	}

	public static void deleteCategory(String database, int id) {
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

	public static void deleteProduct(String database, int id) {
		String sql = "DELETE FROM stok WHERE id = ?";
		dbName = database;
		int stokid = id;
		try (Connection conn = DataBaseHelper.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, stokid);
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

	public static void upgradeProduct(String barkod, String productname, int productquantity, String category,
			Double cost, int id) {
		String sql = "UPDATE STOK SET barkod= ?, urun_adi= ?, urun_adet= ?, kategori= ?, maliyet= ? WHERE id= ?";

		try (Connection conn = DataBaseHelper.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, barkod);
			pstmt.setString(2, productname);
			pstmt.setInt(3, productquantity);
			pstmt.setString(4, category);
			pstmt.setDouble(5, cost);
			pstmt.setInt(6, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static class VeriModel {
		private int id;
		private String barkod;
		private String urun_Adi;
		private int urun_Adet;
		private String kategori;
		private double maliyet;
		private String ad; // Kategori için

		// Stok tablosu için constructor
		public VeriModel(int id, String barkod, String urun_Adi, int urun_Adet, String kategori, double maliyet) {
			this.id = id;
			this.barkod = barkod;
			this.urun_Adi = urun_Adi;
			this.urun_Adet = urun_Adet;
			this.kategori = kategori;
			this.maliyet = maliyet;
		}

		// Kategori tablosu için constructor
		public VeriModel(int id, String ad) {
			this.id = id;
			this.ad = ad;
		}

		// Getter metodları
		public int getId() {
			return id;
		}

		public String getBarkod() {
			return barkod;
		}

		public String getUrunAdi() {
			return urun_Adi;
		}

		public int getUrunAdet() {
			return urun_Adet;
		}

		public String getKategori() {
			return kategori;
		}

		public double getMaliyet() {
			return maliyet;
		}

		public String getAd() {
			return ad;
		}
	}

	public static ObservableList<VeriModel> loadData(String tabloAdi) {
		ObservableList<VeriModel> veriList = FXCollections.observableArrayList();
		dbName = tabloAdi;
		try (Connection conn = connect()) {
			String sql = switch (tabloAdi) {
			case "kategoriler" -> "SELECT id, ad FROM kategoriler";
			case "stok" -> "SELECT id, barkod, urun_adi, urun_adet, kategori, maliyet FROM stok";
			default -> {
				System.out.println("Hatalı tablo adı: " + tabloAdi);
				yield null;
			}
			};
			if (sql == null)
				return veriList;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if (tabloAdi.equals("kategoriler")) {
					veriList.add(new VeriModel(rs.getInt("id"), rs.getString("ad")));
				} else if (tabloAdi.equals("stok")) {
					veriList.add(new VeriModel(rs.getInt("id"), rs.getString("barkod"), rs.getString("urun_adi"),
							rs.getInt("urun_adet"), rs.getString("kategori"), rs.getDouble("maliyet")));
				}
			}
		} catch (SQLException e) {
			System.out.println("Veri yükleme hatası: " + e.getMessage());
		}

		return veriList;
	}

	public static ObservableList<VeriModel> stoklariAra(String aramaMetni) {
		ObservableList<VeriModel> urunListesi = FXCollections.observableArrayList();

		String sql = "SELECT * FROM stok WHERE " + "barkod LIKE ? OR " + "urun_adi LIKE ? OR " + "kategori LIKE ?"; // Arama
																													// sorgusu

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + aramaMetni + "%"); // "%" ekleyerek içinde geçenleri buluyoruz
			pstmt.setString(2, "%" + aramaMetni + "%"); // "%" ekleyerek içinde geçenleri buluyoruz
			pstmt.setString(3, "%" + aramaMetni + "%"); // "%" ekleyerek içinde geçenleri buluyoruz

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				urunListesi.add(new VeriModel(rs.getInt("id"), rs.getString("barkod"), rs.getString("urun_adi"),
						rs.getInt("urun_adet"), rs.getString("kategori"), rs.getDouble("maliyet")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return urunListesi;
	}

	public static ObservableList<VeriModel> kategoriFiltreleme(String aramaMetni) {
		ObservableList<VeriModel> urunListesi = FXCollections.observableArrayList();

		String sql = "SELECT * FROM stok WHERE kategori LIKE ?"; // Arama
																	// sorgusu

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, aramaMetni); // "%" ekleyerek içinde geçenleri buluyoruz

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				urunListesi.add(new VeriModel(rs.getInt("id"), rs.getString("barkod"), rs.getString("urun_adi"),
						rs.getInt("urun_adet"), rs.getString("kategori"), rs.getDouble("maliyet")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return urunListesi;
	}

}
