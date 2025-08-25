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

	public static void createTable(String tablo, Map<String, String> sütunlar, List<String> kısıtlamalar) {
		StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tablo + " (");
		int i = 0;
		for (Map.Entry<String, String> kolon : sütunlar.entrySet()) {
			sql.append(kolon.getKey()).append(" ").append(kolon.getValue());

			if (i < sütunlar.size() - 1) {
				sql.append(",");
			}
			i++;
		}

		if (kısıtlamalar != null && !kısıtlamalar.isEmpty()) {
			sql.append(", ");
			for (int j = 0; j < kısıtlamalar.size(); j++) {
				sql.append(kısıtlamalar.get(j));
				if (j < kısıtlamalar.size() - 1) {
					sql.append(", ");
				}
			}
		}
		sql.append(");");

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute("PRAGMA foreign_keys = ON;");
			stmt.execute(sql.toString());
			System.out.println(tablo + " oluşturuldu");
		} catch (SQLException e) {
			System.out.println(tablo + " Tablo oluşturma hatası: " + e.getMessage());
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
				VeriModel v = new VeriModel();

				try {
					v.setUrunId(rs.getInt("urun_id"));
				} catch (Exception e) {
				}
				try {
					v.setUrunId(rs.getInt("id"));
				} catch (Exception e) {
				}
				try {
					v.setHamMaddeId(rs.getInt("hammadde_id"));
				} catch (Exception e) {
				}
				try {
					v.setMiktar(rs.getDouble("miktar"));
				} catch (Exception e) {
				}
				try {
					v.setBarkod(rs.getString("barkod"));
				} catch (Exception e) {
				}
				try {
					v.setUrunAdi(rs.getString("urun_adi"));
				} catch (Exception e) {
				}
				try {
					v.setUrunAdet(rs.getDouble("urun_adet"));
				} catch (Exception e) {
				}
				try {
					v.setBirim(rs.getString("birim"));
				} catch (Exception e) {
				}
				try {
					v.setKategori(rs.getString("kategori_ad"));
				} catch (Exception e) {
				}
				try {
					v.setKategoriId(rs.getInt("id"));
				} catch (Exception e) {
				}
				try {
					v.setMaliyet(rs.getDouble("maliyet"));
				} catch (Exception e) {
				}
				try {
					v.setParaBirimi(rs.getString("para_birimi"));
				} catch (Exception e) {
				}
				liste.add(v);
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
