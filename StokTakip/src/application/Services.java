package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class Services {
	public void kategoriEkle(String kategoriAdı) {
		if (!degerVarMi("kategoriler", "ad", kategoriAdı)) {
			DataBaseHelper.insertTable("kategoriler", "id,ad", sonId("kategoriler"), kategoriAdı);
			System.out.println("Bu kategori başarıyla eklendi.");
		} else {
			System.out.println("Bu kategori zaten var!");
		}
	}

	public void kategoriSil(int kategoriId) {
		DataBaseHelper.deleteValueTable("kategoriler", "id=?", kategoriId);
	}

	public void ürünEkle(String barkod, String ürünAdi, Double ürünMiktari, String birim, String kategori,
			Double maliyet) {
		if (!degerVarMi("ürünler", "barkod", barkod) || !degerVarMi("ürünler", "urun_adi", ürünAdi)) {
			DataBaseHelper.insertTable("ürünler", "id,barkod,urun_adi,urun_adet,birim,kategori_ad,maliyet",
					sonId("ürünler"), barkod, ürünAdi, ürünMiktari, birim, kategori, maliyet);
		} else {
			System.out.println("Bu ürün zaten var!");
		}
	}

	public void içindekileriEkle(int ürünBilesenleri, Double miktar, String birim) {
		DataBaseHelper.insertTable("product_ingredients", "urun_id,hammadde_id,miktar,birim", sonId("ürünler"),
				ürünBilesenleri, miktar, birim);
		System.out.println("İçindekiler başarıl birşekilde eklendi.");
	}

	public void ürünSil(int ürünId) {
		DataBaseHelper.deleteValueTable("ürünler", "id=?", ürünId);
	}

	public void ürünGüncelle(int ürünId, String barkod, String ürünAdi, Double ürünMiktari, String birim,
			String kategori, Double maliyet) {
		Map<String, Object> yeniDeger = new HashMap<String, Object>();
		yeniDeger.put("barkod", barkod);
		yeniDeger.put("urun_adi", ürünAdi);
		yeniDeger.put("urun_adet", ürünMiktari);
		yeniDeger.put("birim", birim);
		yeniDeger.put("kategori_ad", kategori);
		yeniDeger.put("maliyet", maliyet);

		Map<String, Object> kosul = new HashMap<String, Object>();
		kosul.put("id", ürünId);

		DataBaseHelper.updateTable("ürünler", yeniDeger, kosul);
	}

	public ObservableList<VeriModel> ürünListele(String tabloAdi) {
		ObservableList<VeriModel> liste = DataBaseHelper.listele("*", tabloAdi, null, null, null);
		return liste;
	}

	public ObservableList<VeriModel> stokAra(String tabloAdi, String arammaMetni) {
		Map<String, Pair<String, Object>> sorgu = new HashMap<String, Pair<String, Object>>();
		sorgu.put("barkod", new Pair<>("LIKE", ("%" + arammaMetni + "%")));
		sorgu.put("urun_adi", new Pair<>("LIKE", ("%" + arammaMetni + "%")));
		ObservableList<VeriModel> liste = DataBaseHelper.listele("*", tabloAdi, null, sorgu, "OR");
		return liste;
	}

	public ObservableList<VeriModel> kategoriFiltrele(String tabloAdi, String arammaMetni) {
		Map<String, Pair<String, Object>> sorgu = new HashMap<String, Pair<String, Object>>();
		sorgu.put("kategori_ad", new Pair<>("=", arammaMetni));
		ObservableList<VeriModel> liste = DataBaseHelper.listele("*", tabloAdi, null, sorgu, "OR");
		return liste;
	}

	public boolean tabloVarMi(String tabloAdi) {
		boolean kontrol = false;
		Map<String, Pair<String, Object>> sorgu = new HashMap<String, Pair<String, Object>>();
		sorgu.put("type", new Pair<String, Object>("=", "table"));
		sorgu.put("name", new Pair<String, Object>("=", tabloAdi));
		ObservableList<VeriModel> liste = DataBaseHelper.listele("*", "sqlite_master", null, sorgu, "AND");
		if (!liste.isEmpty()) {
			kontrol = true;
		}
		return kontrol;
	}

	boolean degerVarMi(String tabloAdi, String sütunAdi, String arananDeger) {
		boolean kontrol = false;
		Map<String, Pair<String, Object>> sorgu = new HashMap<String, Pair<String, Object>>();
		sorgu.put(sütunAdi, new Pair<String, Object>("=", arananDeger));
		ObservableList<VeriModel> liste = DataBaseHelper.listele("*", tabloAdi, null, sorgu, "OR");
		if (!liste.isEmpty()) {
			kontrol = true;
		}
		return kontrol;
	}

	int sonId(String tabloAdi) {
		int lastId = DataBaseHelper.getLastInsertedProductId(tabloAdi);
		return lastId;
	}

	public void maliyetGüncelle(int güncellenenBilesenId, Double yeniBilesenMaliyet) {
		Map<String, Pair<String, Object>> sorgu = new HashMap<String, Pair<String, Object>>();
		sorgu.put("hammadde_id", new Pair<String, Object>("=", güncellenenBilesenId));
		ObservableList<VeriModel> kullanılanÜrünlerliste = DataBaseHelper.listele("*", "product_ingredients", null,
				sorgu, null);

		for (VeriModel ürün : kullanılanÜrünlerliste) {
			int ürünId = ürün.getUrunId();
			Map<String, Pair<String, Object>> sorgu2 = new HashMap<String, Pair<String, Object>>();
			sorgu2.put("urun_id", new Pair<String, Object>("=", ürünId));
			ObservableList<VeriModel> kullanılanBilesenListe = DataBaseHelper.listele("*", "product_ingredients", null,
					sorgu2, null);

			Double toplamMaliyet = 0.0;

			for (VeriModel bilesen : kullanılanBilesenListe) {
				int bilesenId = bilesen.getHamMaddeId();
				double bilesenmiktar = bilesen.getMiktar();

				Map<String, Pair<String, Object>> sorgu3 = new HashMap<String, Pair<String, Object>>();
				sorgu3.put("id", new Pair<String, Object>("=", bilesenId));
				ObservableList<VeriModel> bilesenMaliyetListe = DataBaseHelper.listele("*", "ürünler", null, sorgu3,
						null);
				System.out.println(bilesen.getHamMaddeId() + " hammaddeid");
				System.out.println(bilesen.getMiktar() + " miktar");
				System.out.println(bilesen.getUrunId() + " ürün id");

				if (!bilesenMaliyetListe.isEmpty()) {
					if (bilesenId == güncellenenBilesenId) {
						toplamMaliyet += (bilesenmiktar * yeniBilesenMaliyet);
					} else {
						toplamMaliyet += (bilesenmiktar * bilesenMaliyetListe.get(0).getMaliyet());
					}
				}
			}

			Map<String, Object> yeniDeger = new HashMap<String, Object>();
			yeniDeger.put("maliyet", toplamMaliyet);

			Map<String, Object> kosul = new HashMap<String, Object>();
			kosul.put("id", ürünId);

			DataBaseHelper.updateTable("ürünler", yeniDeger, kosul);
		}
	}

	public String getBilesenler(int ürünId) {
		StringBuilder bilesenListesi = new StringBuilder();

		Map<String, Pair<String, String>> join = new HashMap<String, Pair<String, String>>();
		join.put("ürünler u", new Pair<String, String>("JOIN", "pi.hammadde_id = u.id"));

		Map<String, Pair<String, Object>> sorgu = new HashMap<String, Pair<String, Object>>();
		sorgu.put("pi.urun_id", new Pair<String, Object>("=", ürünId));

		ObservableList<VeriModel> liste = DataBaseHelper.listele("u.urun_adi, pi.miktar, u.birim, u.maliyet",
				"product_ingredients pi", join, sorgu, null);

		if (liste.isEmpty()) {
			return "İÇİNDEKİLER\\nBulunamadı...";
		}

		for (int i = 0; i < liste.size(); i++) {
			bilesenListesi.append(liste.get(i).getUrunAdi().toUpperCase()).append(": ").append(liste.get(i).getMiktar())
					.append(" ").append(liste.get(i).getBirim().toUpperCase()).append(" Maliyet(1 Birim): ")
					.append(liste.get(i).getMaliyet()).append("\n");
		}
		return bilesenListesi.toString();
	}
}
