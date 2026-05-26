class Nasabah {
    private String namaLengkap;
    
    // AGREGASI (Weak HAS-A): Nasabah menampung maksimal 3 rekening
    private Rekening[] profilRekening;
    private int jumlahRekeningTerdaftar;

    public Nasabah(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        this.profilRekening = new Rekening[1];
        this.jumlahRekeningTerdaftar = 0;
    }

    public String getNama() {
        return namaLengkap;
    }

    // Metode menyuntikkan rekening (Bukti Agregasi: loose-coupling)
    public void tambahRekeningKeProfil(Rekening rek) {
        if (jumlahRekeningTerdaftar < 3) {
            profilRekening[jumlahRekeningTerdaftar] = rek;
            jumlahRekeningTerdaftar++;
            System.out.println("Rekening " + rek.getNomorRekening() + " berhasil ditautkan ke profil " + namaLengkap);
        } else {
            System.out.println("Profil sudah mencapai batas maksimal 3 rekening.");
        }
    }

    public void tampilkanProfil() {
        System.out.println("\n--- PROFIL NASABAH ---");
        System.out.println("Nama: " + namaLengkap);
        for (int i = 0; i < jumlahRekeningTerdaftar; i++) {
            profilRekening[i].cetakInfo();
        }
    }

    // ASOSIASI (Uses-A): Interaksi tanpa kepemilikan lewat parameter
    public void hubungiCS(CustomerService cs, String keluhan) {
        cs.tanganiKeluhan(this.namaLengkap, keluhan);
    }
}