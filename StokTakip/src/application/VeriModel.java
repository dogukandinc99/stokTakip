package application;

public class VeriModel {
	private int id, ürün_id, hammadde_id;
	private Double miktar;
	private String barkod;
	private String urun_Adi;
	private Double urun_Adet;
	private String birim;
	private String kategori;
	private double maliyet;
	private String ad; // Kategori için

	// ürünler
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

	// kategoriler
	public VeriModel(int id, String ad) {
		this.id = id;
		this.ad = ad;
	}

	// ingredients
	public VeriModel(int ürün_id, int hammadde_id, Double miktar, String birim) {
		this.ürün_id = ürün_id;
		this.hammadde_id = hammadde_id;
		this.miktar = miktar;
		this.birim = birim;
	}

	// sqlite_master
	public VeriModel(String ad) {
		this.ad = ad;
	}

	public int getUrunId() {
		return ürün_id;
	}

	public int getHamMaddeId() {
		return hammadde_id;
	}

	public Double getMiktar() {
		return miktar;
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
