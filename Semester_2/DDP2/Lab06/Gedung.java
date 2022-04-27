package DDP2.Lab06;

import java.util.Arrays;

public class Gedung extends Tempat {
    private String[] listRuangan = new String[0];

    public Gedung(String nama, String lokasi, String deskripsi) {
        // TODO: Selesaikan bagian ini!
        // NOTE: Tipe tempat: gedung
        super(nama, lokasi, deskripsi, "gedung");
    }

    public String[] listSemuaRuangan() {
        return this.listRuangan;
    }

    public void tambahRuangan(String namaRuangan) {
        String[] temp = Arrays.copyOf(this.listRuangan, this.listRuangan.length + 1);
        temp[this.listRuangan.length] = namaRuangan;
        this.listRuangan = temp;
    }

    // This method is to check if certain room exist by using for loop
    public boolean ruanganAda(String ruangan) {
        for (String ruang : listRuangan) {
            if (ruang.equals(ruangan)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String beraktivitas(String aktivitas) {
        // TODO: Selesaikan bagian ini!
        // Checking if the room exist, and return the string formatted
        if (ruanganAda(aktivitas))
            return String.format("Saya belajar di ruangan %s", aktivitas);
        else
            return String.format("Tidak ada ruangan bernama %s", aktivitas);
    }

    @Override
    public String toString() {
        // TODO: Selesaikan bagian ini!
        // Formatted like in the docs
        return String.format("Nama: %s\nLokasi: %s\nDeskripsi: %s\nJumlah Ruangan: %d\nSudah dikunjungi: %b",
                super.getNama(), super.getLokasi(), super.getDeskripsi(), this.listRuangan.length,
                super.getDikunjungi());
    }
}
