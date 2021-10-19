package DDP2.TP3;

class Makanan {
    // Change the modifier into private to secure the variables
    private String nama;
    private long harga;

    Makanan(String nama, long harga) {
        // Set the constructor with the nama and harga
        this.nama = nama;
        this.harga = harga;
    }

    // Getter for variables since it's private
    public String getNama() {
        return this.nama;
    }

    public long getHarga() {
        return this.harga;
    }

    public String toString() {
        // toString return nama
        return nama;
    }
}