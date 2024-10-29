import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Buku> daftarBuku = new ArrayList<>();
        ArrayList<Anggota> daftarAnggota = new ArrayList<>();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        daftarBuku.add(new Buku("C++", 3));
        daftarBuku.add(new Buku("Java", 2));
        daftarBuku.add(new Buku("HTML", 1));
        daftarAnggota.add(new Anggota("Dimas"));
        daftarAnggota.add(new Anggota("Rizky"));

        int pilihan;
        do {
            System.out.println("\n=== Menu Perpustakaan ===");
            System.out.println("1. Tampilkan Buku");
            System.out.println("2. Peminjaman Buku");
            System.out.println("3. Pengembalian Buku");
            System.out.println("4. Tampilkan Buku yang Dipinjam");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 :
                    tampilkanBuku(daftarBuku);
                    break;
                case 2 :
                    prosesTransaksi(scanner, daftarBuku, daftarAnggota, daftarTransaksi);
                break;
                case 3 :
                    prosesPengembalian(scanner, daftarTransaksi);
                break;
                case 4 :
                    tampilkanBukuDipinjam(daftarTransaksi);
                break;
                case 5 :
                    System.out.println("Terima kasih.");
                break;
                default :
                    System.out.println("Pilihan tidak valid.");
                break;
            }
        } while (pilihan != 5);

        scanner.close();
    }

    private static void tampilkanBuku(ArrayList<Buku> daftarBuku) {
        for (Buku buku : daftarBuku) {
            System.out.println(buku.getJudul() + " (Stok: " + buku.getStok() + ")");
        }
    }

    private static void prosesTransaksi(Scanner scanner, ArrayList<Buku> daftarBuku, ArrayList<Anggota> daftarAnggota, ArrayList<Transaksi> daftarTransaksi) {
        System.out.println("Pilih Buku:");
        for (int i = 0; i < daftarBuku.size(); i++) {
            System.out.println((i + 1) + ". " + daftarBuku.get(i).getJudul());
        }
        System.out.print("Masukkan nomor buku: ");
        int nomorBuku = scanner.nextInt();
        scanner.nextLine();

        if (nomorBuku > 0 && nomorBuku <= daftarBuku.size()) {
            Buku buku = daftarBuku.get(nomorBuku - 1);
            System.out.println("Pilih Anggota:");
            for (int i = 0; i < daftarAnggota.size(); i++) {
                System.out.println((i + 1) + ". " + daftarAnggota.get(i).getNama());
            }
            System.out.print("Masukkan nomor anggota: ");
            int nomorAnggota = scanner.nextInt();
            scanner.nextLine();

            if (nomorAnggota > 0 && nomorAnggota <= daftarAnggota.size()) {
                Anggota anggota = daftarAnggota.get(nomorAnggota - 1);
                Transaksi transaksi = new Peminjaman(buku, anggota);
                transaksi.prosesTransaksi();
                daftarTransaksi.add(transaksi);
            } else {
                System.out.println("Nomor anggota tidak valid.");
            }
        } else {
            System.out.println("Nomor buku tidak valid.");
        }
    }

    private static void prosesPengembalian(Scanner scanner, ArrayList<Transaksi> daftarTransaksi) {
        System.out.println("=== Pengembalian Buku ===");

        for (int i = 0; i < daftarTransaksi.size(); i++) {
            Transaksi transaksi = daftarTransaksi.get(i);
            System.out.println((i + 1) + ". " + transaksi.getBuku().getJudul());
        }

        System.out.print("Masukkan nomor buku yang ingin dikembalikan: ");
        int nomorTransaksi = scanner.nextInt();
        scanner.nextLine();

        if (nomorTransaksi > 0 && nomorTransaksi <= daftarTransaksi.size()) {
            Transaksi transaksi = daftarTransaksi.get(nomorTransaksi - 1);
            Transaksi pengembalian = new Pengembalian(transaksi.getBuku(), transaksi.getAnggota());
            pengembalian.prosesTransaksi();
            transaksi.getBuku().tambahStok();
            daftarTransaksi.remove(transaksi);
        } else {
            System.out.println("Nomor buku tidak valid.");
        }
    }

    private static void tampilkanBukuDipinjam(ArrayList<Transaksi> daftarTransaksi) {
        System.out.println("Daftar Buku yang Dipinjam:");
        for (Transaksi transaksi : daftarTransaksi) {
            System.out.println(transaksi.getAnggota().getNama() + " meminjam " + transaksi.getBuku().getJudul());
        }
    }
}
