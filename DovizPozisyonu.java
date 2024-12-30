import java.util.*;

public class DovizPozisyonu {
    private Queue<DovizIslem> islemGecmisi;
    private Map<String, Double> bakiyeler= new HashMap<>();
    private final Map<String, Double> KURLAR = new HashMap<>() {{
        put("TRY-USD", 31.95);
        put("TRY-EUR", 34.85);
        put("TRY-GBP", 40.55);
    }};
    public Map<String, Double> getBakiyeler() {
        return bakiyeler;
    }

    // DOVIZ_CIFTLERI sabiti ekleniyor
    private static final String[] DOVIZ_CIFTLERI = {"TRY-USD", "TRY-EUR", "TRY-GBP"};

    public DovizPozisyonu() {
        this.islemGecmisi = new LinkedList<>();
        this.bakiyeler = new HashMap<>() {{
            put("USD", 0.0);
            put("EUR", 0.0);
            put("GBP", 0.0);
        }};
    }

    public boolean dovizAl(String dovizCifti, double tryMiktar) {
        if (!Arrays.asList(DOVIZ_CIFTLERI).contains(dovizCifti)) return false;

        String dovizTuru = dovizCifti.split("-")[1];
        double kur = KURLAR.get(dovizCifti);
        double dovizMiktar = tryMiktar / kur;

        bakiyeler.put(dovizTuru, bakiyeler.get(dovizTuru) + dovizMiktar);
        islemGecmisi.add(new DovizIslem(dovizCifti, dovizMiktar, true, kur));
        return true;
    }

    public boolean dovizSat(String dovizCifti, double dovizMiktar) {
        String dovizTuru = dovizCifti.split("-")[1];
        if (bakiyeler.get(dovizTuru) < dovizMiktar) return false;

        double kur = KURLAR.get(dovizCifti);
        bakiyeler.put(dovizTuru, bakiyeler.get(dovizTuru) - dovizMiktar);
        islemGecmisi.add(new DovizIslem(dovizCifti, dovizMiktar, false, kur));
        return true;
    }

    public void islemGecmisiniGoster() {
        System.out.println("\nİşlem Geçmişi:");
        for (DovizIslem islem : islemGecmisi) {
            System.out.printf("%s: %.2f %s - Kur: %.2f - %s\n",
                    islem.getDovizCifti(),
                    islem.getMiktar(),
                    islem.getDovizCifti().split("-")[1],
                    islem.getKur(),
                    islem.isAlim() ? "ALIM" : "SATIM"
            );
        }
    }

    public void bakiyeleriGoster() {
        TerminalBox.printBoxedMessage(
                "Döviz Bakiyeleri:",
                "EUR: " + String.format("%.2f €", bakiyeler.get("EUR")),
                "GBP: " + String.format("%.2f £", bakiyeler.get("GBP")),
                "USD: " + String.format("%.2f $", bakiyeler.get("USD"))
        );
    }


    public Map<String, Double> getKurlar() {
        return KURLAR;
    }
}
