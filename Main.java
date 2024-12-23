import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Kullanici> kullanicilar = new HashMap();
        kullanicilar.put("123", new Kullanici("123", "pass1", "Ahmet", "Yılmaz", "TR001", new Hesap(2400.67),
                new Kart(56.99, 19344.01)));
        kullanicilar.put("456",
                new Kullanici("456", "pass2", "Mehmet", "Kaya", "TR002", new Hesap(5000.0), new Kart(100.5, 22000.75)));
        kullanicilar.put("789", new Kullanici("789", "pass3", "Ayse", "Demir", "TR003", new Hesap(1500.3),
                new Kart(30.75, 18000.0)));
        System.out.println("=== YALOVA EKONOMi BANKASI ===");
        System.out.print("Kullanici ID: ");
        String id = scanner.nextLine();
        System.out.print("Sifre: ");
        String sifre = scanner.nextLine();
        Kullanici kullanici = (Kullanici) kullanicilar.get(id);
        if (kullanici != null && kullanici.getSifre().equals(sifre)) {
            System.out.println("\nGiris Basarili!\n");

            while (true) {
                System.out.println("\n--- Islem Menüsü ---");
                System.out.println("1. Hesap Bilgilerini Görüntüle");
                System.out.println("2. Para Transferi Yap");
                System.out.println("3. Borc öd6de");
                System.out.println("4. Cıkıs");
                System.out.print("Seciminiz (1-4): ");
                int secim = scanner.nextInt();
                scanner.nextLine();
                switch (secim) {
                    case 1:
                        hesapBilgileriniGoster(kullanici);
                        break;
                    case 2:
                        paraTransferiYap(scanner, kullanici, kullanicilar);
                        break;
                    case 3:
                        borcOde(scanner, kullanici);
                        break;
                    case 4:
                        System.out.println("Cıkıs yapılıyor...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Gecersiz secim!");
                }
            }
        } else {
            System.out.println("Giris Basarisiz! Kullanici ID veya sifre hatali.");
        }
    }

    private static void hesapBilgileriniGoster(Kullanici kullanici) {
        System.out.println("\n--- Hesap Bilgileriniz ---");
        PrintStream var10000 = System.out;
        String var10001 = kullanici.getAd();
        var10000.println("Ad Soyad: " + var10001 + " " + kullanici.getSoyad());
        System.out.println("IBAN: " + kullanici.getIban());
        System.out.println("Hesap Bakiyesi: " + kullanici.getHesap().getBakiye() + " TL");
        System.out.println("\n--- Kart Bilgileriniz ---");
        System.out.println("Guncel Borc: " + kullanici.getKart().getGuncelBorc() + " TL");
        System.out.println("Kullanilabilir Limit: " + kullanici.getKart().getLimit() + " TL");
    }

    private static void paraTransferiYap(Scanner scanner, Kullanici gonderen, Map<String, Kullanici> kullanicilar) {
        System.out.print("Alıcı IBAN: ");
        String aliciIban = scanner.nextLine();
        if (aliciIban.equals(gonderen.getIban())) {
            System.out.println("Hata: Kendine para transferi yapamazsın! :)");
        } else {
            Kullanici alici = null;
            Iterator var5 = kullanicilar.values().iterator();

            while (var5.hasNext()) {
                Kullanici k = (Kullanici) var5.next();
                if (k.getIban().equals(aliciIban)) {
                    alici = k;
                    break;
                }
            }

            if (alici == null) {
                System.out.println("Gecersiz IBAN!");
            } else {
                try {
                    System.out.print("Transfer tutarı: ");
                    double tutar = Double.parseDouble(scanner.nextLine());
                    if (tutar <= 0.0) {
                        System.out.println("Gecersiz tutar!");
                        return;
                    }

                    if (tutar > gonderen.getHesap().getBakiye()) {
                        System.out.println("Yetersiz bakiye!");
                        return;
                    }

                    gonderen.getHesap().setBakiye(gonderen.getHesap().getBakiye() - tutar);
                    alici.getHesap().setBakiye(alici.getHesap().getBakiye() + tutar);
                    System.out.println("Transfer basarılı!");
                    System.out.println("Yeni bakiyeniz: " + gonderen.getHesap().getBakiye() + " TL");
                } catch (NumberFormatException var7) {
                    System.out.println(
                            "Gecersiz tutar formatı! Lütfen sayısal bir deger giriniz.");
                }

            }
        }
    }

    private static void borcOde(Scanner scanner, Kullanici kullanici) {
        System.out.println("Güncel kart borcunuz: " + kullanici.getKart().getGuncelBorc() + " TL");
        System.out.print("ödenecek tutar: ");
        double tutar = scanner.nextDouble();
        if (tutar <= 0.0) {
            System.out.println("Gecersiz tutar!");
        } else if (tutar > kullanici.getHesap().getBakiye()) {
            System.out.println("Yetersiz bakiye!");
        } else if (tutar > kullanici.getKart().getGuncelBorc()) {
            System.out.println("Borc tutarından fazla ödeme yapamazsınız!");
        } else {
            kullanici.getHesap().setBakiye(kullanici.getHesap().getBakiye() - tutar);
            kullanici.getKart().setGuncelBorc(kullanici.getKart().getGuncelBorc() - tutar);
            System.out.println("Borc ödeme islemi basarılı!");
            System.out.println("Kalan borc: " + kullanici.getKart().getGuncelBorc() + " TL");
            System.out.println("Yeni bakiyeniz: " + kullanici.getHesap().getBakiye() + " TL");
        }
    }
}
