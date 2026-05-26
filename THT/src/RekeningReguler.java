class RekeningReguler extends Rekening {
    private static final double BIAYA_ADMIN = 2500;

    public RekeningReguler(String nomorRekening, String namaPemilik, double saldoAwal, String pin) {
        super(nomorRekening, namaPemilik, saldoAwal, pin);
    }

    @Override
    public void tarik(double nominal) {
        double totalPotongan = nominal + BIAYA_ADMIN;
        if (getSaldo() >= totalPotongan) {
            setSaldo(getSaldo() - totalPotongan);
            System.out.println("Tarik tunai berhasil.");
        } else {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi (Termasuk Admin Rp2.500).");
        }
    }
}