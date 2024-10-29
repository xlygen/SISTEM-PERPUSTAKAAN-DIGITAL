public abstract class Transaksi {
    protected Buku buku;
    protected Anggota anggota;

    public Transaksi(Buku buku, Anggota anggota) {
        this.buku = buku;
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public abstract void prosesTransaksi();
}
