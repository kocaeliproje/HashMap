
public class DovizIslem {
    private String dovizCifti;
    private double miktar;
    private boolean isAlim;
    private double kur;

    public DovizIslem(String dovizCifti, double miktar, boolean isAlim, double kur) {
        this.dovizCifti = dovizCifti;
        this.miktar = miktar;
        this.isAlim = isAlim;
        this.kur = kur;
    }


    public String getDovizCifti() { return dovizCifti; }
    public double getMiktar() { return miktar; }
    public boolean isAlim() { return isAlim; }
    public double getKur() { return kur; }



}