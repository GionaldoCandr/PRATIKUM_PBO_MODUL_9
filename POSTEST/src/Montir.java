class Montir {
    String idMontir;
    String nama;

    public Montir(String idMontir, String nama) {
        this.idMontir = idMontir;
        this.nama = nama;
    }

    // Montir dan Mobil hanya berinteraksi sementara lewat parameter
    public void lakukanQualityControl(Mobil m) {
        System.out.println("Montir " + nama + " (ID: " + idMontir + ") sedang menginspeksi kelayakan " + m.merkMobil + " warna " + m.warna + "...");
    }
}
