import java.util.Stack;

public class SifreYoneticisi {
    private String dogruSifre;
    private Stack<String> sifreDenemeleri;

    public SifreYoneticisi(String dogruSifre) {
        this.dogruSifre = dogruSifre;
        this.sifreDenemeleri = new Stack<>();
    }

    public boolean sifreDene(String girilenSifre) {
        if (girilenSifre.equals(dogruSifre)) {
            sifreDenemeleri.clear();
            System.out.println("Şifre doğru! Giriş başarılı.");
            return true;
        } else {
            sifreDenemeleri.push(girilenSifre);
            if (sifreDenemeleri.size() >= 3) {
                System.out.println("Hesap bloke oldu! 3 kez yanlış deneme.");
                return false;
            } else {
                int kalanHak = 3 - sifreDenemeleri.size();
                System.out.println("Yanlış şifre. Kalan hakkınız: " + kalanHak);
                return false;
            }
        }
    }
}
