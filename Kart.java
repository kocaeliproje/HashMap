class Kart {
    private double guncelBorc;
    private double limit;

    public Kart(double guncelBorc, double limit) {
        this.guncelBorc = guncelBorc;
        this.limit = limit;
    }

    public double getGuncelBorc() {
        return this.guncelBorc;
    }

    public void setGuncelBorc(double guncelBorc) {
        this.guncelBorc = guncelBorc;
    }

    public double getLimit() {
        return this.limit;
    }
}