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
	public static Connection connect() {

		String DB_URL = "jdbc:sqlite:database/urunstokdatabase.db";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("SQLite bağlantısı başarılı!");

		} catch (SQLException e) {
			System.out.println("Bağlantı hatası: " + e.getMessage());
		}
		return conn;
	}

	public static void createTable(String sql, String tabloName) {

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute("PRAGMA foreign_keys = ON;");
			stmt.execute(sql);
			System.out.println(tabloName + " oluşturuldu");
		} catch (SQLException e) {
			System.out.println(tabloName + " Tablo oluşturma hatası: " + e.getMessage());
		}
	}

	public static boolean tabloVarMi(String tableName) {
		String sql = "SELECT COUNT(*) FROM sqlite_master WHERE type= 'table' AND name= ? ";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, tableName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			System.out.println("Kategori kontrol hatası: " + e.getMessage());
		}
		return false;
	}

	public static void kategoriEkle(String tabloName, String ad) {
		String sql = "INSERT INTO kategoriler (ad) VALUES (?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, ad);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static void addProduct(String barkod, String productname, int productquantity, String unit, int category,
			Double cost) {
		String sql = "INSERT INTO ürünler (barkod, urun_adi, urun_adet, birim, kategori_id, maliyet) VALUES (?,?,?,?,?,?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, barkod);
			pstmt.setString(2, productname);
			pstmt.setInt(3, productquantity);
			pstmt.setString(4, unit);
			pstmt.setInt(5, category);
			pstmt.setDouble(6, cost);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static int getLastInsertedProductId() {
		int lastId = -1;

		String sql = "SELECT id FROM ürünler ORDER BY id DESC LIMIT 1";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				lastId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastId;
	}

	public static void addProductIngredients(int urun_id, int hammadde_id, double adet) {
		String sql = "INSERT INTO product_ingredients (urun_id,hammadde_id,miktar) VALUES (?,?,?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, urun_id);
			pstmt.setInt(2, hammadde_id);
			pstmt.setDouble(3, adet);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static boolean degerVarMi(String tableName, String sütunAdi, String kategoriAdi) {
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + sütunAdi + " = ?";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, kategoriAdi);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			System.out.println("Kontrol hatası: " + e.getMessage());
		}
		return false;
	}

	public static void deleteCategory(String database, int id) {
		String sql = "DELETE FROM kategoriler WHERE id = ?";
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
		String sql = "DELETE FROM ürünler WHERE id = ?";
		int stokid = id;
		try (Connection conn = DataBaseHelper.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, stokid);
			int silinenSatir = pstmt.executeUpdate();
			if (silinenSatir > 0) {
				System.out.println("Ürün başarıyla silindi.");
			} else {
				System.out.println("Ürün bulunamadı.");
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

		public VeriModel(int id, String barkod, String urun_Adi, int urun_Adet, String kategori, double maliyet) {
			this.id = id;
			this.barkod = barkod;
			this.urun_Adi = urun_Adi;
			this.urun_Adet = urun_Adet;
			this.kategori = kategori;
			this.maliyet = maliyet;
		}

		public VeriModel(int id, String ad) {
			this.id = id;
			this.ad = ad;
		}

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
		try (Connection conn = connect()) {
			String sql = switch (tabloAdi) {
			case "kategoriler" -> "SELECT id, ad FROM kategoriler";
			case "ürünler" ->
				"SELECT u.*, k.ad AS kategori_adi FROM ürünler u JOIN kategoriler k ON u.kategori_id = k.id;";
			default -> {
				System.out.println("Hatalı tablo adı: " + tabloAdi);
				yield null;
			}
			};
			if (sql == null) {
				return veriList;
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if (tabloAdi.equals("kategoriler")) {
					veriList.add(new VeriModel(rs.getInt("id"), rs.getString("ad")));
				} else if (tabloAdi.equals("ürünler")) {
					veriList.add(new VeriModel(rs.getInt("id"), rs.getString("barkod"), rs.getString("urun_adi"),
							rs.getInt("urun_adet"), rs.getString("kategori_adi").toUpperCase(),
							rs.getDouble("maliyet")));
				}
			}
		} catch (SQLException e) {
			System.out.println("Veri yükleme hatası: " + e.getMessage());
		}

		return veriList;
	}

	public static ObservableList<VeriModel> stoklariAra(String aramaMetni) {
		ObservableList<VeriModel> urunListesi = FXCollections.observableArrayList();

		String sql = "SELECT u.*, k.ad AS kategori_adi FROM ürünler u " + "JOIN kategoriler k ON u.kategori_id = k.id"
				+ " WHERE " + "barkod LIKE ? OR " + "urun_adi LIKE ? ";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + aramaMetni + "%");
			pstmt.setString(2, "%" + aramaMetni + "%");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				urunListesi.add(new VeriModel(rs.getInt("id"), rs.getString("barkod"), rs.getString("urun_adi"),
						rs.getInt("urun_adet"), rs.getString("kategori_adi").toUpperCase(), rs.getDouble("maliyet")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return urunListesi;
	}

	public static ObservableList<VeriModel> kategoriFiltreleme(int aramaMetni) {
		ObservableList<VeriModel> urunListesi = FXCollections.observableArrayList();

		String sql = "SELECT u.*, k.ad AS kategori_adi"
				+ " FROM ürünler u JOIN kategoriler k ON u.kategori_id = k.id WHERE kategori_id = ? ";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, aramaMetni);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				urunListesi.add(new VeriModel(rs.getInt("id"), rs.getString("barkod"), rs.getString("urun_adi"),
						rs.getInt("urun_adet"), rs.getString("kategori_adi").toUpperCase(), rs.getDouble("maliyet")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return urunListesi;
	}

}
