package DDP2.Lab06;

public class LabKomputer extends Tempat {
    private int jumlahKomputer;

    public LabKomputer(String nama, String lokasi, String deskripsi, int jumlahKomputer) {
        // TODO: Selesaikan bagian ini!
        // NOTE: Tipe tempat: labkom
        super(nama, lokasi, deskripsi, "labkom");
        this.jumlahKomputer = jumlahKomputer;
    }

    @Override
    public String beraktivitas(String aktivitas) {
        // TODO: Selesaikan bagian ini!
        // Formatted like in the docs
        return String.format("Saya menggunakan komputer di %s", getNama());
    }

    @Override
    public String toString() {
        // TODO: Selesaikan bagian ini!
        // Formatted like in the docs
        return String.format("Nama: %s\nLokasi: %s\nDeskripsi: %s\nJumlah Komputer tersedia: %d\nSudah dikunjungi: %b",
                getNama(), getLokasi(), getDeskripsi(), jumlahKomputer, getDikunjungi());
    }
}
