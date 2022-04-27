package DDP2.TP3;

class ElemenKantin extends ElemenFasilkom {
    // Change the modifier into private to secure the variables
    private Makanan[] daftarMakanan = new Makanan[10];

    // New method totalMakanan get the total Makanan that already added
    private int totalMakanan = 0;

    public ElemenKantin(String nama) {
        // Set the constructor with the name and status of element type as "elemenKantin"
        this.nama = nama;
        this.tipe = "elemenKantin";
    }

    public void setMakanan(String nama, long harga) {
        // setMakanan method is to add Makanan to daftarMakanan into daftarMakanan array
        // First check if Makanan exist, if yes reject, else add new Makanan object and add to array
        if (makananAda(nama)) {
            System.out.println("[DITOLAK] " + nama + " sudah pernah terdaftar");
        } else {
            daftarMakanan[totalMakanan++] = new Makanan(nama, harga);
            System.out.println(this.nama + " telah mendaftarkan makanan " + nama + " dengan harga " + harga);
        }
    }

    // New makananAda method is to find if a Makanan is exist in daftarMakanan
    public boolean makananAda(String nama) {
        boolean temp = false;
        for (int i = 0; i < totalMakanan; i++) {
            if (daftarMakanan[i].getNama().equals(nama))
                temp = true;
        }
        return temp;
    }

    // New dapatkanMakanan method is to get Makanan from daftarMakanan array
    public Makanan dapatkanMakanan(String nama) {
        Makanan temp = daftarMakanan[0];
        for (int i = 0; i < totalMakanan; i++) {
            if (daftarMakanan[i].getNama().equals(nama))
                temp = daftarMakanan[i];
        }
        return temp;
    }
}