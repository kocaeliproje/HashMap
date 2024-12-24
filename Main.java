/**
 * @author Emrecan Kadirdag 220101055
 * @author Kadir Kagan kaya 220101059
 * @author Mehmet Aydın  220101100
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Kullanici> kullanicilar = new HashMap<>();

        kullanicilar.put("123", new Kullanici("123", "pass1", "Emrecan", "Kadirdag", "TR055", new Hesap(2400)));
        kullanicilar.put("456", new Kullanici("456", "pass2", "Kadir", "Kaya", "TR059", new Hesap(5000)));
        kullanicilar.put("789", new Kullanici("789", "pass3", "Mehmet", "Aydin", "TR001", new Hesap(17000)));
        System.out.println("=== YALOVA EKONOMI BANKASI ===");

        Kullanici kullanici = null;
        while (true) {
            System.out.print("Kullanici ID: ");
            String id = scanner.nextLine();
            System.out.print("Sifre: ");
            String sifre = scanner.nextLine();

            kullanici = kullanicilar.get(id);

            if (kullanici != null && kullanici.getSifre().equals(sifre)) {
                System.out.println("\nGiris Basarili!\n");
                break;
            } else {
                System.out.println("Hatalı giriş! Tekrar deneyin.");
            }
        }

        menuGoster(scanner, kullanici, kullanicilar);
    }

    private static void menuGoster(Scanner scanner, Kullanici kullanici, Map<String, Kullanici> kullanicilar) {
        while (true){    //sonsuz donguye almak icin true aldık
            System.out.println("\n--- Menü ---");
            System.out.println("1. Hesap Bilgileri");
            System.out.println("2. Para Transferi");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminiz (1-3): ");
            int secim = scanner.nextInt(); //kullanıcıdan aldıgımız degeri secime atadık
            scanner.nextLine();

            switch (secim) {
                case 1:
                    hesapBilgileriniGoster(kullanici);
                    break;
                case 2:
                    paraTransferiYap(scanner, kullanici, kullanicilar);
                    break;
                case 3:
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void hesapBilgileriniGoster(Kullanici kullanici) {
        System.out.println("\n--- Hesap Bilgileri ---");
        System.out.println("Ad Soyad: " + kullanici.getAd() + " " + kullanici.getSoyad());
        System.out.println("IBAN: " + kullanici.getIban());
        System.out.println("Bakiye: " + kullanici.getHesap().getBakiye() + " TL");
    }

    private static void paraTransferiYap(Scanner scanner, Kullanici gonderen, Map<String, Kullanici> kullanicilar) {
        System.out.print("Alıcı IBAN: ");
        String aliciIban = scanner.nextLine();

        Kullanici alici = null;
        for (Kullanici k : kullanicilar.values()) {
            if (k.getIban().equals(aliciIban)) {
                alici = k;
                break;
            }
        }

        if (alici == null) {
            System.out.println("IBAN bulunamadı!");
            return;
        }

        System.out.print("Transfer tutarı: ");
        double tutar = scanner.nextDouble();

        if (tutar <= 0 || tutar > gonderen.getHesap().getBakiye()) {
            System.out.println("Geçersiz tutar veya yetersiz bakiye!");
            return;
        }

        gonderen.getHesap().setBakiye(gonderen.getHesap().getBakiye() - tutar);
        alici.getHesap().setBakiye(alici.getHesap().getBakiye() + tutar);

        System.out.println("Transfer başarılı!");
        System.out.println("Yeni bakiyeniz: " + gonderen.getHesap().getBakiye() + " TL");
    }
}
