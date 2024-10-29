public class Peminjaman extends Transaksi {
    public Peminjaman(Buku buku, Anggota anggota) {
        super(buku, anggota);
    }

    @Override
    public void prosesTransaksi() {
        if (buku.getStok() > 0) {
            buku.kurangiStok();
            System.out.println("Status: Buku " + buku.getJudul() + " dipinjam oleh " + anggota.getNama() + ".");
        } else {
            System.out.println("Stok buku habis.");
        }
    }
}
