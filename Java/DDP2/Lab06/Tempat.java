package DDP2.Lab06;

import java.util.Arrays;

public class Tempat {
    private String nama;
    private String lokasi;
    private String deskripsi;
    private String tipeTempat;
    private boolean dikunjungi = false;
    static Tempat[] semuaTempat = new Tempat[0];

    public Tempat(String nama, String lokasi, String deskripsi, String tipeTempat) {
        // TODO: Tambahkan method Getter dan Setter sesuai keperluan.
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.tipeTempat = tipeTempat;
    }

    // Getter setter needed in subclasses's toString()
    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return this.lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return this.deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTipeTempat() {
        return this.tipeTempat;
    }

    public void setTipeTempat(String tipeTempat) {
        this.tipeTempat = tipeTempat;
    }

    public boolean isDikunjungi() {
        return this.dikunjungi;
    }

    public boolean getDikunjungi() {
        return this.dikunjungi;
    }

    public void setDikunjungi(boolean dikunjungi) {
        this.dikunjungi = dikunjungi;
    }

    public String kunjungi() {
        // TODO: Selesaikan bagian ini!
        // When a place is visited, dikunjungi becomes true, and the return formatted
        // like in docs
        this.dikunjungi = true;
        return String.format("Saya mengunjungi %s", nama);
    }

    public String beraktivitas(String aktivitas, String namaTempat) {
        Tempat tempat = getTempat(namaTempat);
        if (tempat.getTipeTempat().equals("gedung") && aktivitas.toLowerCase().startsWith("belajar")) {
            return tempat.beraktivitas(aktivitas.substring(8));
        } else if (tempat.getTipeTempat().equals("labkom")
                && aktivitas.toLowerCase().equalsIgnoreCase("gunakan komputer")) {
            return tempat.beraktivitas(aktivitas);
        }
        return String.format("Di %s, aktivitas %s tidak dapat dilakukan.", namaTempat, aktivitas);
    }

    public String beraktivitas(String aktivitas) {
        return String.format("Saya melakukan %s di %s.", aktivitas, this.nama);
    }

    public String toString() {
        return String.format("Nama: %s\nLokasi: %s\nDeskripsi: %s\nSudah dikunjungi: %b", nama, lokasi, deskripsi,
                dikunjungi);
    }

    public static Tempat getTempat(String namaTempat) {
        for (Tempat tempat : Tempat.semuaTempat) {
            if (tempat.getNama().equals(namaTempat)) {
                return tempat;
            }
        }
        return null;
    }

    public static void tambahArrayTempat(Tempat value) {
        Tempat[] temp = Arrays.copyOf(semuaTempat, semuaTempat.length + 1);
        temp[semuaTempat.length] = value;
        semuaTempat = temp;
    }
}