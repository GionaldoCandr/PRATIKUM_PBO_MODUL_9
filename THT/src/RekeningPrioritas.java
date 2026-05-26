class RekeningPrioritas extends Rekening {
    private static final double MIN_TARIK = 100000;

    public RekeningPrioritas(String nomorRekening, String namaPemilik, double saldoAwal, String pin) {
        super(nomorRekening, namaPemilik, saldoAwal, pin);
    }

    @Override
    public void tarik(double nominal) {
        if (nominal < MIN_TARIK) {
            System.out.println("Transaksi Gagal: Rekening Prioritas memiliki batas minimum penarikan Rp" + MIN_TARIK);
            return;
        }
        
        if (getSaldo() >= nominal) {
            setSaldo(getSaldo() - nominal);
            System.out.println("Tarik tunai berhasil (Bebas Biaya Admin).");
        } else {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi.");
        }
    }
}
