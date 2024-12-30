public class Kullanici {
    private String id;
    private String sifre;
    private String ad;
    private String soyad;
    private String iban;
    private Hesap hesap;
    private DovizPozisyonu dovizPozisyonu;
    private SifreYoneticisi sifreYoneticisi;

    public Kullanici(String id, String sifre, String ad, String soyad, String iban, Hesap hesap) {
        this.id = id;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.iban = iban;
        this.hesap = hesap;
        this.dovizPozisyonu = new DovizPozisyonu();
        this.sifreYoneticisi = new SifreYoneticisi(sifre);
    }
    public boolean sifreDogrula(String girilenSifre) {
        return sifreYoneticisi.sifreDene(girilenSifre);
    }


    public String getSifre() { return sifre; }
    public String getAd() { return ad; }
    public String getSoyad() { return soyad; }
    public String getIban() { return iban; }
    public Hesap getHesap() { return hesap; }
    public DovizPozisyonu getDovizPozisyonu() { return dovizPozisyonu; }
}
