public class Kullanici{
    private String id;
    private String sifre;
    private String ad;
    private String soyad;
    private String iban;
    private Hesap hesap;

    public Kullanici(String id, String sifre, String ad, String soyad, String iban, Hesap hesap) {
        this.id = id;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.iban = iban;
        this.hesap = hesap;
    }

    public String getSifre() {
        return sifre;
    }
    public String getAd() {
        return ad;
    }
    public String getSoyad() {
        return soyad;
    }
    public String getIban() {
        return iban;
    }
    public Hesap getHesap() {
        return hesap;
    }

}
