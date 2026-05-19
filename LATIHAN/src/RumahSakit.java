class RumahSakit {
    
    // bagian fisik yang tidak terpisahkan composisi
    private final Ruangan[] daftarRuangan;
    
    // kontraktor yang bisa datang dan pergi agregasi
    private Dokter[] daftarDokter;

    public RumahSakit() {
        // Objek Ruangan diciptakan mutlak di dalam sistem Rumah Sakit
        this.daftarRuangan = new Ruangan[2];
        this.daftarRuangan[0]= new Ruangan("R-01", 2);
        this.daftarRuangan[1] = new Ruangan("R-02", 3);
    }

    // Menugaskan dokter 
    public void tugaskanTimMedis(Dokter[] timMedis) {
        this.daftarDokter = timMedis;
    }

    public void cetakDaftarRuangan() {
        System.out.println("DAFTAR INFRASTRUKTUR RUANGAN :");
        for (Ruangan r : daftarRuangan) {
            if (r != null) r.infoRuangan();
        }
    }

    public void cetakDaftarDokter() {
        System.out.println(" DAFTAR TENAGA MEDIS BERTUGAS :");
        if (this.daftarDokter != null) {
            for (Dokter d : daftarDokter) {
                if (d != null) d.infoDokter();
            }
        }
    }
}