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
	static Connection connect() {

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

	public static void addProduct(String barkod, String productname, Double productquantity, String unit, int category,
			Double cost) {
		String sql = "INSERT INTO ürünler (barkod, urun_adi, urun_adet, birim, kategori_id, maliyet) VALUES (?,?,?,?,?,?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, barkod);
			pstmt.setString(2, productname);
			pstmt.setDouble(3, productquantity);
			pstmt.setString(4, unit);
			pstmt.setInt(5, category);
			pstmt.setDouble(6, cost);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Kategori ekleme hatası: " + e.getMessage());
		}
	}

	public static void addProductIngredients(int urun_id, int hammadde_id, double adet, String birim) {
		String sql = "INSERT INTO product_ingredients (urun_id,hammadde_id,miktar,birim) VALUES (?,?,?,?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, urun_id);
			pstmt.setInt(2, hammadde_id);
			pstmt.setDouble(3, adet);
			pstmt.setString(4, birim);
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

	public static void upgradeProduct(String barkod, String productname, Double productquantity, String unit,
			int category, Double cost, int id) {
		String sql = "UPDATE ürünler SET barkod= ?, urun_adi= ?, urun_adet= ?, birim= ?, kategori_id= ?, maliyet= ? WHERE id= ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, barkod);
			pstmt.setString(2, productname);
			pstmt.setDouble(3, productquantity);
			pstmt.setString(4, unit);
			pstmt.setInt(5, category);
			pstmt.setDouble(6, cost);
			pstmt.setInt(7, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ingredientsList(int hammaddeId, double yeniHammaddeMaliyet) {
		String productIngredientsSql = "Select urun_id, miktar FROM product_ingredients WHERE hammadde_id= ?";
		String ürünlerSql = "SELECT pi.hammadde_id, pi.miktar, u.maliyet FROM product_ingredients pi "
				+ "JOIN ürünler u ON pi.hammadde_id = u.id WHERE pi.urun_id = ?";
		String ürünGüncelle = "UPDATE ürünler SET maliyet= ? WHERE id= ? ";

		try (Connection conn = connect();
				PreparedStatement urunBulStmt = conn.prepareStatement(productIngredientsSql);
				PreparedStatement tumHamMaddelerStmt = conn.prepareStatement(ürünlerSql);
				PreparedStatement maliyetGuncelleStmt = conn.prepareStatement(ürünGüncelle)) {

			// 1. Bu ham maddeyi kullanan tüm ürünleri bul
			urunBulStmt.setInt(1, hammaddeId);
			ResultSet urunRs = urunBulStmt.executeQuery();

			while (urunRs.next()) {
				int urunId = urunRs.getInt("urun_id");

				// 2. Ürünün içindeki tüm ham maddeleri ve miktarlarını getir
				tumHamMaddelerStmt.setInt(1, urunId);
				ResultSet hamMaddeRs = tumHamMaddelerStmt.executeQuery();

				double yeniToplamMaliyet = 0.0;

				while (hamMaddeRs.next()) {
					int mevcutHammaddeId = hamMaddeRs.getInt("hammadde_id");
					double miktar = hamMaddeRs.getDouble("miktar");
					double hammaddeMaliyeti = hamMaddeRs.getDouble("maliyet");

					// 3. Eğer güncellenen ham madde buysa yeni maliyeti kullan, değilse eski
					// maliyeti
					if (mevcutHammaddeId == hammaddeId) {
						yeniToplamMaliyet += (miktar * yeniHammaddeMaliyet);
					} else {
						yeniToplamMaliyet += (miktar * hammaddeMaliyeti);
					}
				}

				// 4. Ürünün yeni maliyetini güncelle
				maliyetGuncelleStmt.setDouble(1, yeniToplamMaliyet);
				maliyetGuncelleStmt.setInt(2, urunId);
				maliyetGuncelleStmt.executeUpdate();

				System.out.println("Ürün ID: " + urunId + " yeni maliyet: " + yeniToplamMaliyet);
			}

		} catch (SQLException e) {
			System.out.println("Maliyet güncelleme hatası: " + e.getMessage());
		}
	}

	public static class VeriModel {
		private int id;
		private String barkod;
		private String urun_Adi;
		private Double urun_Adet;
		private String birim;
		private String kategori;
		private double maliyet;
		private String ad; // Kategori için

		public VeriModel(int id, String barkod, String urun_Adi, Double urun_Adet, String birim, String kategori,
				double maliyet) {
			this.id = id;
			this.barkod = barkod;
			this.urun_Adi = urun_Adi;
			this.urun_Adet = urun_Adet;
			this.birim = birim;
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

		public Double getUrunAdet() {
			return urun_Adet;
		}

		public String getBirim() {
			return birim;
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
							rs.getDouble("urun_adet"), rs.getString("birim"),
							rs.getString("kategori_adi").toUpperCase(), rs.getDouble("maliyet")));
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
						rs.getDouble("urun_adet"), rs.getString("birim"), rs.getString("kategori_adi").toUpperCase(),
						rs.getDouble("maliyet")));
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
						rs.getDouble("urun_adet"), rs.getString("birim"), rs.getString("kategori_adi").toUpperCase(),
						rs.getDouble("maliyet")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return urunListesi;
	}

	public static String getHamMaddeler(int urunId) {
		StringBuilder hamMaddelListesi = new StringBuilder();

		String sql = "SELECT u.urun_adi, pi.miktar, u.birim, u.maliyet"
				+ " FROM product_ingredients pi JOIN ürünler u ON pi.hammadde_id = u.id where pi.urun_id = ? ";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, urunId);

			ResultSet rs = pstmt.executeQuery();

			boolean varMı = false;
			hamMaddelListesi.append("İÇİNDEKİLER\n");
			while (rs.next()) {
				varMı = true;
				hamMaddelListesi.append(rs.getString("urun_adi").toUpperCase()).append(": ")
						.append(rs.getDouble("miktar")).append(" ").append(rs.getString("birim"))
						.append(" Maliyet(1 Adet): ").append(rs.getString("maliyet")).append("\n");

			}
			if (!varMı) {
				return "İÇİNDEKİLER\nBulunamadı...";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return hamMaddelListesi.toString();
	}

}
