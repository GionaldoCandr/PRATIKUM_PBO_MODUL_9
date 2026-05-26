import java.util.*;
// Interface Otorisasi
interface Otorisasi {
    boolean verifikasiPIN(String pinInput);
}

// Class Komponen untuk Komposisi
class BukuMutasi {
    public void catatLog(String aktivitas) {
        System.out.println("[LOG MUTASI] " + aktivitas);
    }
}

// Abstract Class Rekening
abstract class Rekening implements Otorisasi {
    protected String nomorRekening;
    protected String namaPemilik;
    private double saldo; // ENKAPSULASI KETAT
    private String pin;
    
    // KOMPOSISI (Strong HAS-A)
    private BukuMutasi bukuMutasi;

    public Rekening(String nomorRekening, String namaPemilik, double saldoAwal, String pin) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldoAwal;
        this.pin = pin;
        
        // Jika objek Rekening hancur, BukuMutasi akan ikut musnah.
        this.bukuMutasi = new BukuMutasi();
        this.bukuMutasi.catatLog("Rekening baru berhasil dibuka dengan saldo awal Rp" + saldoAwal);
    }

    // Enkapsulasi: Saldo tidak bisa diubah langsung, hanya bisa dibaca
    public double getSaldo() {
        return saldo;
    }

    // Metode internal untuk mengubah saldo (digunakan oleh subclass)
    protected void setSaldo(double saldoBaru) {
        this.saldo = saldoBaru;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    // Implementasi Interface Otorisasi
    @Override
    public boolean verifikasiPIN(String pinInput) {
        return this.pin.equals(pinInput);
    }

    // Metode Konkret (Setor)
    public void setor(double nominal) {
        if (nominal > 0) {
            this.saldo += nominal;
            this.bukuMutasi.catatLog("Setor tunai Rp" + nominal + " | Saldo Akhir: Rp" + this.saldo);
            System.out.println("Setoran berhasil!");
        } else {
            System.out.println("Nominal setoran tidak valid.");
        }
    }

    // Abstract Method (Tarik) untuk memicu Polymorphism
    public abstract void tarik(double nominal);
    
    public void cetakInfo() {
        System.out.println("No. Rekening: " + nomorRekening + " | Saldo: Rp" + saldo);
    }
}

public class NeoBankSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Simulasi Bank Data Pusat (Untuk menyimpan rekening secara independen)
        List<Rekening> bankDataPusat = new ArrayList<>();
        
        Nasabah nasabahAktif = null;
        CustomerService csNeoBank = new CustomerService();
        int nomorUrutRekening = 1001;

        boolean isRunning = true;


        while (isRunning) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Registrasi Profil Nasabah Baru");
            System.out.println("2. Buka Rekening Baru (Reguler/Prioritas)");
            System.out.println("3. Login & Transaksi (Setor/Tarik)");
            System.out.println("4. Hubungi Customer Service (Asosiasi)");
            System.out.println("5. Tutup Akun Secara Paksa (Test Relasi)");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih menu > ");
            int pilihan = input.nextInt();
            input.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    if (nasabahAktif == null) {
                        System.out.print("Masukkan Nama Lengkap Anda: ");
                        String nama = input.nextLine();
                        nasabahAktif = new Nasabah(nama);
                        System.out.println("[SUKSES] Profil Nasabah '" + nama + "' berhasil dibuat.");
                    } else {
                        System.out.println("[INFO] Anda sudah memiliki profil aktif.");
                    }
                    break;

                case 2:
                    if (nasabahAktif == null) {
                        System.out.println("[GAGAL] Silakan Registrasi Profil terlebih dahulu (Menu 1).");
                        break;
                    }
                    System.out.println("Pilih Jenis Rekening:");
                    System.out.println("1. Reguler (Ada biaya admin tarik tunai Rp2.500)");
                    System.out.println("2. Prioritas (Bebas biaya admin, min tarik Rp100.000)");
                    System.out.print("Pilihan > ");
                    int jenis = input.nextInt();
                    input.nextLine();

                    System.out.print("Masukkan PIN baru (6 digit): ");
                    String pinBaru = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: Rp ");
                    double saldoAwal = input.nextDouble();
                    
                    String noRek = "NEO-" + nomorUrutRekening++;
                    Rekening rekBaru = null;

                    if (jenis == 1) {
                        rekBaru = new RekeningReguler(noRek, nasabahAktif.getNama(), saldoAwal, pinBaru);
                    } else if (jenis == 2) {
                        rekBaru = new RekeningPrioritas(noRek, nasabahAktif.getNama(), saldoAwal, pinBaru);
                    }

                    if (rekBaru != null) {
                        // Menyimpan ke Data Pusat & Menyuntikkan ke Profil (Agregasi)
                        bankDataPusat.add(rekBaru);
                        nasabahAktif.tambahRekeningKeProfil(rekBaru);
                    }
                    break;

                case 3:
                    if (bankDataPusat.isEmpty()) {
                        System.out.println("[GAGAL] Belum ada rekening yang terdaftar.");
                        break;
                    }
                    
                    System.out.print("Masukkan Nomor Rekening: ");
                    String inputRek = input.nextLine();
                    
                    // Mencari rekening di data pusat
                    Rekening rekLogin = null;
                    for (Rekening r : bankDataPusat) {
                        if (r.getNomorRekening().equals(inputRek)) {
                            rekLogin = r;
                            break;
                        }
                    }

                    if (rekLogin == null) {
                        System.out.println("[GAGAL] Rekening tidak ditemukan.");
                        break;
                    }

                    System.out.print("Masukkan PIN: ");
                    String inputPin = input.nextLine();
                    
                    // ABSTRAKSI (Validasi melalui Interface)
                    if (rekLogin.verifikasiPIN(inputPin)) {
                        System.out.println("\n[LOGIN BERHASIL] Sisa Saldo: Rp" + rekLogin.getSaldo());
                        System.out.print("Pilih Transaksi (1. Setor / 2. Tarik) > ");
                        int trx = input.nextInt();
                        System.out.print("Masukkan Nominal: Rp ");
                        double nominal = input.nextDouble();

                        if (trx == 1) {
                            rekLogin.setor(nominal);
                        } else if (trx == 2) {
                            rekLogin.tarik(nominal); // Memicu Polymorphism
                        }
                    } else {
                        System.out.println("[GAGAL] PIN Salah!");
                    }
                    break;

                case 4:
                    if (nasabahAktif != null) {
                        System.out.print("Tulis keluhan Anda: ");
                        String keluhan = input.nextLine();
                        // ASOSIASI (Uses-A)
                        nasabahAktif.hubungiCS(csNeoBank, keluhan);
                    } else {
                        System.out.println("[GAGAL] Buat profil nasabah terlebih dahulu.");
                    }
                    break;

                case 5:
                    if (nasabahAktif != null) {
                        System.out.println("Menghapus profil nasabah '" + nasabahAktif.getNama() + "' dari sistem...");
                        nasabahAktif = null; // Menghancurkan objek Nasabah
                        System.out.println("[SUKSES] Profil Nasabah telah dihapus permanen (di-set null).");
                        
                        System.out.println("\n--- BUKTI ANALISIS SIKLUS HIDUP OBJEK (PBO) ---");
                        
                        /* 
                         * Meskipun 'nasabahAktif' sudah bernilai null dan musnah dari memori,
                         * entitas 'Rekening' tetap hidup aman di dalam 'bankDataPusat'. 
                         * Ini membuktikan relasi Agregasi (Loose-Coupling) di mana objek bagian 
                         * dapat terus eksis walau pemiliknya hancur.
                         */
                        System.out.println("1. Cek Agregasi: Apakah Rekening di Data Pusat ikut musnah?");
                        if (!bankDataPusat.isEmpty()) {
                            System.out.println("   -> TIDAK! Rekening masih ada di sistem pusat. Jumlah rekening aman: " + bankDataPusat.size());
                            System.out.print("   -> Detail: ");
                            bankDataPusat.get(0).cetakInfo();
                        }

                        /* 
                         * Jika suatu saat kita menghancurkan entitas Rekening itu sendiri
                         * dari data pusat, maka objek 'BukuMutasi' di dalamnya dipastikan ikut musnah.
                         * Mengapa? Karena ia diinstansiasi secara eksklusif (new BukuMutasi()) 
                         * tepat di dalam constructor Rekening dan tidak memiliki referensi dari luar.
                         */
                        System.out.println("\n2. Cek Komposisi: Apa yang terjadi jika Rekening dihapus dari Data Pusat?");
                        bankDataPusat.clear(); // Menghancurkan objek Rekening
                        System.out.println("   -> Rekening telah dilebur. Objek 'BukuMutasi' yang berada di dalamnya");
                        System.out.println("      kini dipastikan ikut musnah tanpa sisa karena siklus hidupnya");
                        System.out.println("      bergantung penuh secara mutlak pada eksistensi Rekening (Strong HAS-A).");
                        
                    } else {
                        System.out.println("[INFO] Tidak ada profil nasabah yang sedang aktif.");
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan NeoBank.");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        input.close();
    }
}
