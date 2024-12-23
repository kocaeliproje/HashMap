class Kullanici {
    private String id;
    private String sifre;
    private String ad;
    private String soyad;
    private String iban;
    private Hesap hesap;
    private Kart kart;

    public Kullanici(String id, String sifre, String ad, String soyad, String iban, Hesap hesap, Kart kart) {
        this.id = id;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.iban = iban;
        this.hesap = hesap;
        this.kart = kart;
    }

    public String getSifre() {
        return this.sifre;
    }

    public String getAd() {
        return this.ad;
    }

    public String getSoyad() {
        return this.soyad;
    }

    public String getIban() {
        return this.iban;
    }

    public Hesap getHesap() {
        return this.hesap;
    }

    public Kart getKart() {
        return this.kart;
    }
}
