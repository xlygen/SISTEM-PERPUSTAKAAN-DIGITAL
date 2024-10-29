public class Pengembalian extends Transaksi {
    public Pengembalian(Buku buku, Anggota anggota) {
        super(buku, anggota);
    }

    @Override
    public void prosesTransaksi() {
        System.out.println("Status: Buku " + buku.getJudul() + " berhasil dikembalikan.");
    }
}
