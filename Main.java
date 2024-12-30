/**
 * @author Emrecan Kadirdag 220101055
 * @author Kadir Kagan kaya 220101059
 * @author Mehmet Aydın  220101100
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Kullanici> kullanicilar = new HashMap<>();

        kullanicilar.put("123", new Kullanici("123", "pass1", "Emrecan", "Kadirdag", "TR055", new Hesap(2400)));
        kullanicilar.put("456", new Kullanici("456", "pass2", "Kadir", "Kaya", "TR059", new Hesap(5000)));
        kullanicilar.put("789", new Kullanici("789", "pass3", "Mehmet", "Aydin", "TR001", new Hesap(17000)));

        TerminalBox.printBoxedMessage("=== YALOVA EKONOMI BANKASI ===");

        Kullanici kullanici = null;
        while (true) {
            System.out.print("Kullanici ID: ");
            String id = scanner.nextLine();
            System.out.print("Sifre: ");
            String sifre = scanner.nextLine();

            kullanici = kullanicilar.get(id);
            if (kullanici != null && kullanici.sifreDogrula(sifre)) {
                TerminalBox.printBoxedMessage("Giriş Başarılı!");
                break;
            }
        }

        menuGoster(scanner, kullanici, kullanicilar);
    }

    private static void menuGoster(Scanner scanner, Kullanici kullanici, Map<String, Kullanici> kullanicilar) {
        while (true) {
            TerminalBox.printBoxedMessage(
                    "--- Menü ---",
                    "1. Hesap Bilgileri",
                    "2. Para Transferi",
                    "3. Döviz İşlemleri",
                    "4. Para Yatır",
                    "5. Çıkış",
                    "Seçiminiz (1-5): "
            );

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1: hesapBilgileriniGoster(kullanici); break;
                case 2: paraTransferiYap(scanner, kullanici, kullanicilar); break;
                case 3: dovizIslemleriYap(scanner, kullanici); break;
                case 4: paraYatirIslemi(scanner, kullanici); break;
                case 5:
                    TerminalBox.printBoxedMessage("Çıkış yapılıyor...");
                    return;
                default:
                    TerminalBox.printBoxedMessage("Geçersiz seçim!");
            }
        }
    }

    private static void paraYatirIslemi(Scanner scanner, Kullanici kullanici) {
        TerminalBox.printBoxedMessage(
                "--- Para Yatırma ---",
                "Mevcut Bakiye: " + kullanici.getHesap().getBakiye() + " TL"
        );
        System.out.print("Yatırmak istediğiniz miktar: ");
        double miktar = scanner.nextDouble();

        if (miktar > 0) {
            kullanici.getHesap().paraYatir(miktar);
            TerminalBox.printBoxedMessage(
                    "Para yatırma işlemi başarılı!",
                    "Yeni bakiyeniz: " + kullanici.getHesap().getBakiye() + " TL"
            );
        } else {
            TerminalBox.printBoxedMessage("Geçersiz miktar!");
        }
    }

    private static void hesapBilgileriniGoster(Kullanici kullanici) {
        TerminalBox.printBoxedMessage(
                "--- Hesap Bilgileri ---",
                "Ad Soyad: " + kullanici.getAd() + " " + kullanici.getSoyad(),
                "IBAN: " + kullanici.getIban(),
                "Bakiye: " + kullanici.getHesap().getBakiye() + " TL"
        );
        kullanici.getDovizPozisyonu().bakiyeleriGoster();
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
            TerminalBox.printBoxedMessage("IBAN bulunamadı!");
            return;
        }

        System.out.print("Transfer tutarı: ");
        double tutar = scanner.nextDouble();

        if (tutar <= 0 || tutar > gonderen.getHesap().getBakiye()) {
            TerminalBox.printBoxedMessage("Geçersiz tutar veya yetersiz bakiye!");
            return;
        }

        gonderen.getHesap().setBakiye(gonderen.getHesap().getBakiye() - tutar);
        alici.getHesap().setBakiye(alici.getHesap().getBakiye() + tutar);

        TerminalBox.printBoxedMessage(
                "Transfer başarılı!",
                "Yeni bakiyeniz: " + gonderen.getHesap().getBakiye() + " TL"
        );
    }

    private static void dovizIslemleriYap(Scanner scanner, Kullanici kullanici) {
        TerminalBox.printBoxedMessage(
                "--- Döviz İşlemleri ---",
                "1. Döviz Al",
                "2. Döviz Sat",
                "3. Bakiyeleri Görüntüle",
                "4. İşlem Geçmişi"
        );
        System.out.print("Seçiminiz (1-4): ");

        int secim = scanner.nextInt();
        scanner.nextLine();

        switch (secim) {
            case 1:
                dovizAlimIslemleri(scanner, kullanici);
                break;
            case 2:
                dovizSatisIslemleri(scanner, kullanici);
                break;
            case 3:
                kullanici.getDovizPozisyonu().bakiyeleriGoster();
                break;
            case 4:
                kullanici.getDovizPozisyonu().islemGecmisiniGoster();
                break;
        }
    }

    private static void dovizAlimIslemleri(Scanner scanner, Kullanici kullanici) {
        TerminalBox.printBoxedMessage("Mevcut Kurlar:");
        kullanici.getDovizPozisyonu().getKurlar().forEach((cift, kur) ->
                System.out.printf("%s: %.2f\n", cift, kur));

        System.out.print("\nDöviz çifti seçin (TRY-USD/TRY-EUR/TRY-GBP): ");
        String dovizCifti = scanner.nextLine();
        System.out.print("TRY miktar: ");
        double tryMiktar = scanner.nextDouble();

        if (tryMiktar <= kullanici.getHesap().getBakiye()) {
            if (kullanici.getDovizPozisyonu().dovizAl(dovizCifti, tryMiktar)) {
                kullanici.getHesap().setBakiye(kullanici.getHesap().getBakiye() - tryMiktar);
                TerminalBox.printBoxedMessage("Döviz alımı başarılı!");
            } else {
                TerminalBox.printBoxedMessage("Geçersiz döviz çifti!");
            }
        } else {
            TerminalBox.printBoxedMessage("Yetersiz bakiye!");
        }
    }

    private static void dovizSatisIslemleri(Scanner scanner, Kullanici kullanici) {
        kullanici.getDovizPozisyonu().bakiyeleriGoster();
        System.out.print("\nDöviz çifti seçin (TRY-USD/TRY-EUR/TRY-GBP): ");
        String dovizCifti = scanner.nextLine();
        System.out.print("Döviz miktar: ");
        double dovizMiktar = scanner.nextDouble();

        if (kullanici.getDovizPozisyonu().dovizSat(dovizCifti, dovizMiktar)) {
            double tryKazanc = dovizMiktar * kullanici.getDovizPozisyonu().getKurlar().get(dovizCifti);
            kullanici.getHesap().setBakiye(kullanici.getHesap().getBakiye() + tryKazanc);
            TerminalBox.printBoxedMessage("Döviz satışı başarılı!");
        } else {
            TerminalBox.printBoxedMessage("Yetersiz döviz bakiyesi!");
        }
    }
}
