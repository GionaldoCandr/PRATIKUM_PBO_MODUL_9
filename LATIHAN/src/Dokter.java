class Dokter {
    String nama;
    String spesialisasi;

    public Dokter(String nama, String spesialisasi) {
        this.nama = nama;
        this.spesialisasi = spesialisasi;
    }
    // Dokter hanya "menggunakan" Pasien sementara waktu
    public void periksaPasien(Pasien pasien) {
        System.out.println("Rekam Jejak: Dokter " + this.nama + " (Spesialis " + this.spesialisasi + 
                           ") sedang memeriksa pasien bernama " + pasien.nama + ".");
    }

    public void infoDokter() {
        System.out.println("- Dr. " + nama + " (Spesialis " + spesialisasi + ")");
    }
}