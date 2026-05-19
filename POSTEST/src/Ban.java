class Ban {
    String merk;
    int ukuranRing;

    public Ban(String merk, int ukuranRing) {
        this.merk = merk;
        this.ukuranRing = ukuranRing;
    }

    public void infoBan() {
        System.out.println("- Ban " + merk + " (Ring " + ukuranRing + ")");
    }
}