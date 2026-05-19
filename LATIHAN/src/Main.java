public class Main {
    public static void main(String[] args) {
        System.out.println("1. INISIALISASI SISTEM PUSAT");
        // Menciptakan setidaknya 2 Dokter dan 2 Pasien secara mandiri 
        Dokter drGio = new Dokter("Gionaldo", "Penyakit Komputer");
        Dokter drDanu = new Dokter("Aldo", "Bedah Mental");
        
        Pasien pasienAldo = new Pasien("Aldo", 19);
        Pasien pasienWisnu = new Pasien("Wisnu", 22);
        System.out.println("[SUKSES] Data tenaga medis dan pengunjung telah terdaftar.");

        System.out.println("\n2. SIMULASI PEMERIKSAAN");
        // Salah satu dokter memeriksa pasien 
        drGio.periksaPasien(pasienAldo);

        System.out.println("\n3. PENDIRIAN RUMAH SAKIT & PENUGASAN");
        // Mendirikan RS, yang secara otomatis membangun ruangannya di dalam (Komposisi) 
        RumahSakit rsSehatSelalu = new RumahSakit();
        
        // Memasukkan para dokter ke dalam RS (Agregasi) 
        Dokter[] timMedisPusat = {drGio, drDanu};
        rsSehatSelalu.tugaskanTimMedis(timMedisPusat);
        
        // Menampilkan seluruh data
        rsSehatSelalu.cetakDaftarRuangan();
        rsSehatSelalu.cetakDaftarDokter();

        System.out.println("\n4. PENGUJIAN AKHIR: RUMAH SAKIT DITIADAKAN");
        // Menghancurkan entitas rumah sakit dari memori
        rsSehatSelalu = null; 
        System.out.println("[SISTEM] Objek 'RS Sehat Selalu' telah dihancurkan (null).");

        System.out.println("\n5. PEMBUKTIAN EKSISTENSI ENTITAS DI MEMORI");
        
        // Eksperimen pemanggilan objek Dokter
        System.out.print("Cek Data Dr. Andi   : ");
        if (drGio != null) {
            System.out.println("MASIH ADA!");
            drGio.infoDokter();
        }

        // Eksperimen pemanggilan objek Pasien
        System.out.print("Cek Data Pasien Ayu : ");
        if (pasienWisnu != null) {
            System.out.println("MASIH ADA!");
            pasienWisnu.infoPasien();
        }

        /* 
 1. ENTITAS RUANGAN -> DIPASTIKAN HILANG (KOMPOSISI). 
    Karena objek array Ruangan dibuat mutlak secara eksklusif DI DALAM 
    konstruktor RumahSakit . Ketika 'rsSehatSelalu' diubah menjadi null, 
    referensi ke Ruangan otomatis terputus dan disapu oleh sistem ,
    karena ia tidak punya eksistensi independen 
 
 2. ENTITAS DOKTER -> MASIH ADA (AGREGASI).
    Objek drAndi dan drSiti diciptakan secara independen di sistem pusat (Main), 
    lalu hanya "disuntikkan" (passing) ke dalam RS. Siklus hidup mereka
    tidak terikat pada RS, sehingga saat RS hancur, data dokter utuh dan bisa
    ditugaskan kembali.
 
 3. ENTITAS PASIEN -> MASIH ADA (ASOSIASI).
    Pasien hanya berinteraksi sementara lewat parameter method 
    'periksaPasien()'. Dokter dan Pasien tidak saling memiliki kepemilikan 
    sehingga pembubaran RS atau Dokter tidak akan melenyapkan data Pasien 
         */
    }
}