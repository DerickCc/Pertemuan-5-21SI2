import java.util.ArrayList;

public class Nasabah {
    private String norek;
    private String nama;
    private double saldo;
    private ArrayList<String> mutasi = new ArrayList<String>();

    public Nasabah(String norek, String nama){
        this.norek = norek;
        this.nama = nama;
        this.saldo = 500000;
    }

    public void tambahSaldo(int nominal){
        this.saldo+=nominal;
    }

    public String getNorek() {
        return this.norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<String> getMutasi() {
        return this.mutasi;
    }

    public void setMutasi(String detail) {
        this.mutasi.add(detail);
    }

    public int sizeMutasi(){
        return this.mutasi.size();
    }

    public void cetakMutasi(){
        for (String detail : mutasi) {
            System.out.println(detail);
        }
    }

    @Override
    public String toString(){
        return "" + getNorek() + "\t" + getNama() + "\t\t" + "Rp."+ App.format(getSaldo());
    }
}
