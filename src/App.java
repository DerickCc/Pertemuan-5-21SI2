import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static String format(double x){
        return String.format("%,.2f", x);
    }
    public static int cekNorek(String norek, ArrayList<Nasabah> nasabah){
        int idx = 0;
        for (Nasabah nsb : nasabah) {
            if((nsb.getNorek()).equalsIgnoreCase(norek)){
                return idx;
            }
            idx++;
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Nasabah> nasabah = new ArrayList<Nasabah>();
        nasabah.add(new Nasabah("0213456", "Susi"));
        nasabah.add(new Nasabah("0314865", "Budi"));

        String yn = "y";

        while(yn.equalsIgnoreCase("y")){
            clearScreen();
            Nasabah norekA, norekT;
            System.out.println("Selamat datang di Bank ABC !");
            System.out.println("----------------------------");
            System.out.println("1. Daftar No. Rekening");
            System.out.println("2. Transfer");
            System.out.println("3. Cetak Log Mutasi Nasabah");
            System.out.println("4. Print Data Semua Nasabah");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda [1/2/3/4/5] ? ");
            int pilihan = keyboard.nextInt();

            if(pilihan==1){
                clearScreen();
                System.out.println("-------------------");
                System.out.println("DAFTAR NO. REKENING");
                System.out.println("-------------------");
                System.out.print("Masukkan No. Rekening yang ingin didaftarkan: ");
                String norekDaftar = keyboard.next();
                if(norekDaftar.length()==7){
                    if(cekNorek(norekDaftar, nasabah)==-1){
                        System.out.print("Masukkan nama pemilik No. Rekening: ");
                        String namaDaftar = keyboard.nextLine();
                        namaDaftar+=keyboard.nextLine();
                        nasabah.add(new Nasabah(norekDaftar, namaDaftar));
                        System.out.println("\nNo. rekening berhasil didaftarkan.");
                    }
                    else{
                        System.out.println("\nNo. rekening sudah terdaftar ...");
                    }
                }
                else{
                    System.out.println("\nNo. rekening harus 7 digit ...");
                } 
            }
            else if(pilihan==2){
                clearScreen();
                System.out.println("--------");
                System.out.println("TRANSFER");
                System.out.println("--------");
                System.out.print("Masukkan No. Rekening asal: ");
                String norekAsal = keyboard.next();
                if(cekNorek(norekAsal,nasabah)>=0){
                    System.out.print("Masukkan No. Rekening tujuan: ");
                    String norekTujuan = keyboard.next();
                    if(cekNorek(norekTujuan,nasabah)>=0){
                        norekA = nasabah.get(cekNorek(norekAsal,nasabah));
                        norekT = nasabah.get(cekNorek(norekTujuan,nasabah));
                        System.out.print("Masukkan nominal transfer: ");
                        int nominal = keyboard.nextInt();
                        if(nominal < norekA.getSaldo() & nominal!=0){
                            norekA.tambahSaldo(nominal*-1);;
                            int tmp = nominal;
                            norekT.tambahSaldo(tmp);
                            norekA.setMutasi("- Transfer ke "+ norekT.getNorek() +" ("+ norekT.getNama()+") sebesar Rp."+ format(nominal));
                            norekT.setMutasi("- Kredit dari "+ norekA.getNorek() +" ("+ norekA.getNama()+") sebesar Rp."+ format(nominal));
                            System.out.println("\n<TRANSAKSI BERHASIL>");
                            System.out.println("Berhasil melakukan transfer sebesar Rp."+ format(nominal) +" ke "+ norekT.getNorek()+" ("+norekT.getNama()+")");
                        }
                        else{
                            System.out.println("\n<TRANSAKSI GAGAL>");
                            System.out.println("Saldo tidak mencukupi...");  
                        }
                    }
                    else{
                        System.out.println("\n<TRANSAKSI GAGAL>");
                        System.out.println("No. Rekening tujuan belum terdaftar...");       
                    }
                }
                else{
                    System.out.println("\n<TRANSAKSI GAGAL>");
                    System.out.println("No. Rekening asal belum terdaftar...");
                }
            }
            else if(pilihan==3){
                clearScreen();
                System.out.println("------------------------");
                System.out.println("CETAK LOG MUTASI NASABAH");
                System.out.println("------------------------");
                System.out.print("Masukkan No. Rekening: ");
                String norekAsal = keyboard.next();
                System.out.println("");
                if(cekNorek(norekAsal, nasabah)>=0){
                    norekA=nasabah.get(cekNorek(norekAsal, nasabah));
                    if(norekA.sizeMutasi()==0){
                        System.out.println("Belum ada transaksi...");
                    }
                    else{
                        norekA.cetakMutasi();;
                    }
                }
                else{
                    System.out.println("No. Rekening tidak valid...");
                }    
            }
            else if(pilihan==4){
                clearScreen();
                System.out.println("------------------------");
                System.out.println("PRINT DATA SEMUA NASABAH");
                System.out.println("------------------------\n");
                System.out.println("No. Rek\tNama\t\tSaldo");
                System.out.println("-------------------------------------");
                for (Nasabah n : nasabah){
                    System.out.println(n);
                }
            }
            else if(pilihan==5){
                break;
            }
            else{
                System.out.println("Pilihan tidak tersedia...");
            }
            System.out.print("\nKembali ke halaman utama [y/n] ? ");
            yn = keyboard.next();
        }
        System.out.println("\nTerima Kasih\n");
        keyboard.close();
    }
}
