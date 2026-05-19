class Mesin {
    String nomorSeri;
    int kapasitasCC;

    public Mesin(String nomorSeri, int kapasitasCC) {
        this.nomorSeri = nomorSeri;
        this.kapasitasCC = kapasitasCC;
    }

    public void infoMesin() {
        System.out.println("- Mesin No. Seri: " + nomorSeri + " (" + kapasitasCC + " CC)");
    }
}