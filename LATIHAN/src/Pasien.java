class Pasien {
    String nama;
    int umur;

    public Pasien(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public void infoPasien() {
        System.out.println("- Pasien: " + nama + " (Umur: " + umur + " tahun)");
    }
}
