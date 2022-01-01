package DDP2.TP3;

abstract class ElemenFasilkom {
    // Add modifier to public because it's an abstract class
    public String tipe;
    public String nama;
    public int friendship = 0;
    public ElemenFasilkom[] telahMenyapa = new ElemenFasilkom[100];

    // Add new variable jumlahMenyapa to get total of menyapa
    public int jumlahMenyapa = 0;

    // Add getter setter
    // (Actually i dont need this since the variables are public, but for sake of consistency for using getter setter)
    public String getTipe() {
        return this.tipe;
    }

    public String getNama() {
        return this.nama;
    }

    public int getFriendship() {
        return this.friendship;
    }

    public void setFriendship(int friendship) {
        this.friendship += friendship;
    }

    // sudahMenyapa is new method to check if an element already disapa
    public boolean sudahMenyapa(ElemenFasilkom elemen) {
        for (int i = 0; i < jumlahMenyapa; i++) {
            if (telahMenyapa[i].equals(elemen)) {
                return true;
            }
        }
        return false;
    }

    public void menyapa(ElemenFasilkom elemenFasilkom) {
        // menyapa method
        // First check if the element already disapa, if yes, reject
        if (sudahMenyapa(elemenFasilkom)) {
            System.out.println("[DITOLAK] " + nama + " telah menyapa " + elemenFasilkom + " hari ini");
        } else {
            // else, add the element to telahMenyapa array, increment jumlahMenyapa, 
            // and add 'this' element into that element's telahMenyapa variable
            telahMenyapa[jumlahMenyapa++] = elemenFasilkom;
            elemenFasilkom.telahMenyapa[elemenFasilkom.jumlahMenyapa++] = this;
            System.out.println(nama + " menyapa dengan " + elemenFasilkom);
        }
    }

    public void resetMenyapa() {
        // resetMenyapa method reset telahMenyapa array and jumlahMenyapa into 0
        for (int i = 0; i < 100; i++) {
            telahMenyapa[i] = null;
        }
        jumlahMenyapa = 0;
    }

    public void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan) {
        // membeliMakanan method
        if (((ElemenKantin) penjual).makananAda(namaMakanan)) {
            System.out.println(pembeli + " berhasil membeli " + namaMakanan + " seharga "
                    + ((ElemenKantin) penjual).dapatkanMakanan(namaMakanan).getHarga());
            pembeli.setFriendship(1);
            penjual.setFriendship(1);
        } else {
            System.out.println("[DITOLAK] " + penjual + " tidak menjual " + namaMakanan);
        }
    }

    public String toString() {
        // toString return nama
        return nama;
    }
}