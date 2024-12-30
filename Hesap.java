public class Hesap {
    private double bakiye;

    public Hesap(double bakiye) {
        this.bakiye = bakiye;
    }

    public double getBakiye() { return bakiye; }
    public void setBakiye(double bakiye) { this.bakiye = bakiye; }

    public void paraYatir(double miktar) {
        if (miktar > 0) {
            this.bakiye += miktar;
        }
    }
}
