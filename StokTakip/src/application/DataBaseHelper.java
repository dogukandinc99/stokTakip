package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

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

	public static void insertTable(String tablo, String kolonlar, Object... degerler) {
		String placeHolders = String.join(",", Collections.nCopies(degerler.length, "?"));
		String sql = "INSERT INTO " + tablo + " (" + (kolonlar) + ") VALUES (" + placeHolders + ")";

		sqlSorguCalistir(sql, degerler);
	}

	public static void updateTable(String tablo, Map<String, Object> yeniDegerler, Map<String, Object> kosullar) {
		StringBuilder sql = new StringBuilder("UPDATE " + tablo + " SET ");
		List<Object> parametreler = new ArrayList<>();

		for (String kolon : yeniDegerler.keySet()) {
			if (!parametreler.isEmpty()) {
				sql.append(", ");
			}
			sql.append(kolon).append("=?");
			parametreler.add(yeniDegerler.get(kolon));
		}

		if (!kosullar.isEmpty()) {
			sql.append(" WHERE ");
			for (String kosul : kosullar.keySet()) {
				if (parametreler.size() > yeniDegerler.size())
					sql.append(" AND ");
				sql.append(kosul).append(" = ?");
				parametreler.add(kosullar.get(kosul));
			}
		}

		sqlSorguCalistir(sql.toString(), parametreler.toArray());
	}

	public static void deleteValueTable(String tablo, String kosul, Object... kosuldegerleri) {
		String placeHolders = String.join(",", Collections.nCopies(kosuldegerleri.length, "?"));
		String sql = "DELETE FROM " + tablo + " WHERE " + kosul;

		sqlSorguCalistir(sql, kosuldegerleri);
	}

	public static ObservableList<VeriModel> listele(String kolonlar, String tablo,
			Map<String, Pair<String, String>> joinler, Map<String, Pair<String, Object>> kosullar,
			String baglantiTipi) {
		ObservableList<VeriModel> liste = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder("SELECT " + kolonlar + " FROM " + tablo + " ");

		List<Object> parametreler = new ArrayList<>();

		if (joinler != null) {
			for (Map.Entry<String, Pair<String, String>> join : joinler.entrySet()) {
				sql.append(join.getValue().getKey()).append(" ").append(join.getKey()).append(" ON ")
						.append(join.getValue().getValue()).append(" ");
			}
		}

		if (kosullar != null && !kosullar.isEmpty()) {
			sql.append("WHERE ");
			int i = 0;
			for (Map.Entry<String, Pair<String, Object>> kosul : kosullar.entrySet()) {
				if (i++ > 0)
					if (baglantiTipi != null) {
						sql.append(" " + baglantiTipi + " ");
					}
				sql.append(kosul.getKey()).append(" ").append(kosul.getValue().getKey()).append(" ?");
				parametreler.add(kosul.getValue().getValue());
			}
		}
		try (ResultSet rs = sqlListeleSorguCalistir(sql.toString(), parametreler.toArray())) {
			while (rs.next()) {
				if (tablo.startsWith("kategoriler")) {
					liste.add(new VeriModel(rs.getInt("id"), rs.getString("ad")));
				} else if (tablo.startsWith("ürünler")) {
					liste.add(new VeriModel(rs.getInt("id"), rs.getString("barkod"), rs.getString("urun_adi"),
							rs.getDouble("urun_adet"), rs.getString("birim"), rs.getString("kategori_ad").toUpperCase(),
							rs.getDouble("maliyet")));
				} else if (tablo.startsWith("sqlite_master")) {
					liste.add(new VeriModel(rs.getString("name")));
				}
			}
		} catch (SQLException e) {
			System.out.println("Listeleme hatası: " + e.getMessage());
		}
		return liste;
	}

	public static ResultSet sqlListeleSorguCalistir(String sql, Object... parametreler) {
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < parametreler.length; i++) {
				pstmt.setObject(i + 1, parametreler[i]);
			}
			return pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void sqlSorguCalistir(String sql, Object... degerler) {
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			for (int i = 0; i < degerler.length; i++) {
				pstmt.setObject(i + 1, degerler[i]);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Sorgu çalıştırılırken bir sorun ile karşılaştım...\n" + e.getMessage());
		}
	}

	public static int getLastInsertedProductId(String tabloAdi) {
		int lastId = 0;

		String sql = "SELECT id FROM " + tabloAdi + " ORDER BY id DESC LIMIT 1";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				lastId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (lastId + 1);
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
