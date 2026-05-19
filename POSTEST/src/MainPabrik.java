public class MainPabrik {
    public static void main(String[] args) {
        
        System.out.println("=== 1. PRODUKSI BAN MANDIRI (AGREGASI) ===");
        // Diciptakan terpisah secara mandiri
        Ban ban1 = new Ban("Michelin", 17);
        Ban ban2 = new Ban("Michelin", 17);
        Ban ban3 = new Ban("Michelin", 17);
        Ban ban4 = new Ban("Michelin", 17);
        Ban[] setBanSiapPasang = {ban1, ban2, ban3, ban4};
        System.out.println("[SUKSES] 4 unit ban selesai diproduksi.\n");

        System.out.println("=== 2. PEMBENTUKAN MOBIL (KOMPOSISI) ===");
        // Saat mobil dibentuk, otomatis mesin tercipta di dalam konstruktornya
        Mobil sedanKeren = new Mobil("Honda Civic RS", "Hitam", "ENG-Z99", 1500);
        System.out.println("[SUKSES] Kerangka mobil dan mesin selesai dirakit.\n");

        System.out.println("3. PEMASANGAN BAN KE MOBIL");
        // Menyuntikkan array ban ke dalam mobil
        sedanKeren.pasangSetBan(setBanSiapPasang);
        sedanKeren.tampilkanSpesifikasi();

        System.out.println("\n4. INSPEKSI OLEH MONTIR (DEPENDENCY)");
        Montir montirAhli = new Montir("MTR-007", "Joko");
        montirAhli.lakukanQualityControl(sedanKeren);

        System.out.println("\n5. SIMULASI MOBIL GAGAL UJI KELAYAKAN ");
        // Menghancurkan entitas mobil dari sistem memori
        sedanKeren = null; 
        System.out.println("[SISTEM] Mobil gagal uji kelayakan dan telah dilebur (objek sedanKeren diset null).\n");

        System.out.println("=== 6. PEMBUKTIAN EKSISTENSI ENTITAS DI MEMORI ===");
        
        // Bukti 1: Cek apakah Ban masih utuh (Agregasi)
        System.out.println("> Mengecek status Ban 1:");
        if (ban1 != null) {
            System.out.print("MASIH ADA! Ban bisa disimpan kembali ke gudang: ");
            ban1.infoBan();
        }

        // Bukti 2: Cek apakah Montir masih utuh (Dependency)
        System.out.println("\n> Mengecek status Montir Joko:");
        if (montirAhli != null) {
            System.out.println("MASIH ADA! Montir Joko siap mengecek mobil berikutnya.");
        }

        /* 
          1. MESIN 
             Mesin dipastikan MUSNAH TANPA JEJAK. Karena objek Mesin di-instansiasi 
             khusus DI DALAM konstruktor Mobil, ia sama sekali tidak memiliki referensi 
             di luar (di class Main). Begitu objek `sedanKeren` dilebur (null), akses 
             ke mesin terputus dan langsung disapu bersih oleh Garbage Collector.
          
          2. BAN 
             Ban MASIH ADA dan BISA DIGUNAKAN LAGI. Ban diinstansiasi secara 
             independen di Main class, barulah kemudian disuntikkan ke mobil lewat
             metode pasangSetBan(). Karenanya, siklus hidup ban tidak terikat 
             pada hancurnya mobil.
          
          3. MONTIR 
             Montir MASIH ADA. Relasi montir dan mobil sangat lepas karena hanya 
             terjadi interaksi sementara lewat parameter fungsi `lakukanQualityControl()`. 
             Tidak ada unsur kepemilikan sama sekali di antara keduanya.
         */
    }
}