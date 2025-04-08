package application;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;

public class Services {
	public void kategoriEkle(String kategoriAdı) {
		if (!DataBaseHelper.degerVarMi("kategoriler", "ad", kategoriAdı)) {
			DataBaseHelper.insertTable("kategoriler", "ad", kategoriAdı);
			System.out.println("Bu kategori başarıyla eklendi.");
		} else {
			System.out.println("Bu kategori zaten var!");
		}
	}

	public void kategoriSil(int kategoriId) {
		DataBaseHelper.deleteValueTable("kategoriler", "id=?", kategoriId);
	}

	public void ürünEkle(int id, String barkod, String ürünAdi, Double ürünMiktari, String birim, String kategori,
			Double maliyet) {
		if (!DataBaseHelper.degerVarMi("ürünler", "urun_adi", ürünAdi)) {
			DataBaseHelper.insertTable("ürünler", "id,barkod,urun_adi,urun_adet,birim,kategori_ad,maliyet", id, barkod,
					ürünAdi, ürünMiktari, birim, kategori, maliyet);
		} else {
			System.out.println("Bu ürün zaten var!");
		}
	}

	public void içindekileriEkle(int ürünId, int ürünBilesenleri, Double miktar, String birim) {
		DataBaseHelper.insertTable("product_ingredients", "urun_id,hammadde_id,miktar,birim", ürünId, ürünBilesenleri,
				miktar, birim);
		System.out.println("İçindekiler başarıl birşekilde eklendi.");
	}

	public void ürünSil(int ürünId) {
		DataBaseHelper.deleteValueTable("ürünler", "id=?", ürünId);
	}

	public void ürünGüncelle(int ürünId, String barkod, String ürünAdi, Double ürünMiktari, String birim, int kategori,
			Double maliyet) {
		Map<String, Object> yeniDeger = new HashMap<String, Object>();
		yeniDeger.put("barkod", barkod);
		yeniDeger.put("urun_adi", ürünAdi);
		yeniDeger.put("urun_adet", ürünMiktari);
		yeniDeger.put("birim", birim);
		yeniDeger.put("kategori_id", kategori);
		yeniDeger.put("maliyet", maliyet);

		Map<String, Object> kosul = new HashMap<String, Object>();
		kosul.put("id", ürünId);

		DataBaseHelper.updateTable("ürünler", yeniDeger, kosul);
	}

	public ObservableList<DataBaseHelper.VeriModel> ürünListele(String tabloAdi) {
		ObservableList<DataBaseHelper.VeriModel> liste = DataBaseHelper.listele("*", tabloAdi, null, null);
		return liste;
	}
}
