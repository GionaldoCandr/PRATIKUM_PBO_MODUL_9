import java.util.ArrayList;
import java.util.List;

class KomputerServer {


    
    private Harddisk harddiskBawaan;
    private List<Monitor> daftarMonitor;

    public KomputerServer(String tipeHDD, int kapasitasHDD) {
        this.harddiskBawaan = new Harddisk(tipeHDD, kapasitasHDD);   
        this.daftarMonitor = new ArrayList<>();
    }


    public void colokMonitor(Monitor monitorEksternal) {
        this.daftarMonitor.add(monitorEksternal); 
    }
}