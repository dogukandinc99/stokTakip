package application;

public class VeriModel {
	private int urun_id, hammadde_id;
	private Double miktar;
	private String barkod;
	private String urun_Adi;
	private Double urun_Adet;
	private String birim;
	private int kategori_id;
	private String kategori;
	private double maliyet;

	public VeriModel() {
	}

	public int getUrunId() {
		return urun_id;
	}

	public void setUrunId(int ürünId) {
		this.urun_id = ürünId;
	}

	public int getKategoriId() {
		return kategori_id;
	}

	public void setKategoriId(int kategoriId) {
		this.kategori_id = kategoriId;
	}

	public int getHamMaddeId() {
		return hammadde_id;
	}

	public void setHamMaddeId(int hammaddeId) {
		this.hammadde_id = hammaddeId;
	}

	public Double getMiktar() {
		return miktar;
	}

	public void setMiktar(Double miktar) {
		this.miktar = miktar;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}

	public String getUrunAdi() {
		return urun_Adi;
	}

	public void setUrunAdi(String ürünAdi) {
		this.urun_Adi = ürünAdi;
	}

	public Double getUrunAdet() {
		return urun_Adet;
	}

	public void setUrunAdet(Double ürünAdet) {
		this.urun_Adet = ürünAdet;
	}

	public String getBirim() {
		return birim;
	}

	public void setBirim(String birim) {
		this.birim = birim;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public double getMaliyet() {
		return maliyet;
	}

	public void setMaliyet(Double maliyet) {
		this.maliyet = maliyet;
	}
}
