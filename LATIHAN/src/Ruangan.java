class Ruangan {
    String nomorRegistrasi;
    int kapasitasMaksimal;

    public Ruangan(String nomorRegistrasi, int kapasitasMaksimal) {
        this.nomorRegistrasi = nomorRegistrasi;
        this.kapasitasMaksimal = kapasitasMaksimal;
    }

    public void infoRuangan() {
        System.out.println("- Ruangan " + nomorRegistrasi + " (Kapasitas: " + kapasitasMaksimal + " Pasien)");
    }
}