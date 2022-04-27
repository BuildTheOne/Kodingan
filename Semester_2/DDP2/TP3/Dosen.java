package DDP2.TP3;

class Dosen extends ElemenFasilkom {
    // Change the modifier into private to secure the variable
    private MataKuliah mataKuliah;

    public Dosen(String nama) {
        // Set the constructor with the name and status of element type as "Dosen"
        this.nama = nama;
        this.tipe = "dosen";
    }

    // Getter needed since the variable is private
    public MataKuliah getMataKuliah() {
        return this.mataKuliah;
    }

    public void mengajarMataKuliah(MataKuliah mataKuliah) {
        // mengajarMataKuliah method add a MataKuliah into variable this.MataKuliah
        // First, check if dosen has taken any mataKuliah, if yes reject
        // Then, check if that MataKuliah already has Dosen, if yes reject
        // Else, set mataKuliah into this.matakuliah and dosen into that mataKuliah
        if (this.mataKuliah != null) {
            System.out.println("[DITOLAK] " + nama + " sudah mengajar mata kuliah " + this.mataKuliah);
        } else if (mataKuliah.getDosen() != null) {
            System.out.println("[DITOLAK] " + mataKuliah + " sudah memiliki dosen pengajar");
        } else {
            this.mataKuliah = mataKuliah;
            mataKuliah.setDosen(this);
            System.out.println(nama + " mengajar mata kuliah " + mataKuliah);
        }
    }

    public void dropMataKuliah() {
        // dropMataKuliah method to remove dosen mataKuliah
        // Check if dosen has not taken any mataKuliah, if yes reject
        // Else, set mataKuliah into null and dosen of that mataKuliah to null
        if (mataKuliah == null) {
            System.out.println("[DITOLAK] " + nama + " sedang tidak mengajar mata kuliah apapun");
        } else {
            System.out.println(nama + " berhenti mengajar " + mataKuliah);
            mataKuliah.setDosen(null);
            mataKuliah = null;
        }
    }

    // This is override method menyapa from ElemenFasilkom class, to accomodate if elemenFasilkom is Mahasiswa object
    // The difference is add a conditional if elemenFasilkom is Mahasiswa object, add this object and that Mahasiswa object's friendship point by 5
    @Override
    public void menyapa(ElemenFasilkom elemenFasilkom) {
        if (sudahMenyapa(elemenFasilkom)) {
            System.out.println("[DITOLAK] " + nama + " telah menyapa " + elemenFasilkom + " hari ini");
        } else {
            telahMenyapa[jumlahMenyapa++] = elemenFasilkom;
            elemenFasilkom.telahMenyapa[elemenFasilkom.jumlahMenyapa++] = this;
            System.out.println(nama + " menyapa dengan " + elemenFasilkom);

            if (elemenFasilkom.getTipe().equals("mahasiswa")
                    && ((Mahasiswa) elemenFasilkom).matkulAda(this.mataKuliah)) {
                this.setFriendship(5);
                elemenFasilkom.setFriendship(5);
            }
        }
    }
}