class Mobil {
    String merkMobil;
    String warna;
    
    //Komponen mutlak yang tak terpisahkan
    private Mesin mesinBawaan;
    
    // Komponen lepas-pasang
    private Ban[] setBan; 

    public Mobil(String merkMobil, String warna, String noSeriMesin, int kapasitasCC) {
        this.merkMobil = merkMobil;
        this.warna = warna;
        
        // Mesin selalu diciptakan langsung DI DALAM konstruktor mobil.
        this.mesinBawaan = new Mesin(noSeriMesin, kapasitasCC);
        
        // Menyiapkan slot untuk maksimal 4 buah ban
        this.setBan = new Ban[5];
    }

    // Ban disuntikkan dari luar setelah mobil dibuat
    public void pasangSetBan(Ban[] setBan) {
        if (setBan.length <= 4) {
            this.setBan = setBan;
        } else {
            System.out.println("Gagal! Slot ban hanya muat untuk 4 buah.");
        }
    }

    public void tampilkanSpesifikasi() {
        System.out.println("SPESIFIKASI MOBIL");
        System.out.println("Merk  : " + merkMobil);
        System.out.println("Warna : " + warna);
        
        System.out.println("\n[Komponen Internal - KOMPOSISI]");
        if (mesinBawaan != null) mesinBawaan.infoMesin();
        
        System.out.println("\n[Komponen Eksternal - AGREGASI]");
        if (setBan != null && setBan != null) {
            for (Ban b : setBan) {
                if (b != null) b.infoBan();
            }
        } else {
            System.out.println("- Ban belum dipasang.");
        }
    }
}
