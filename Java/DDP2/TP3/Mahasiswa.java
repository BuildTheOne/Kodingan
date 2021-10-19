package DDP2.TP3;

class Mahasiswa extends ElemenFasilkom {
    // Change the modifier into private to secure the variables
    private MataKuliah[] daftarMataKuliah = new MataKuliah[10];
    private long npm;
    private String tanggalLahir;
    private String jurusan;

    // New variables totalMatkul to get the total Matkul that already be taken
    private int totalMatkul = 0;

    public Mahasiswa(String nama, long npm) {
        // Set the constructor with the name, npm, status of element type as "Mahasiswa", and tanggalLahir dan Jurusan that get from the methods below
        this.nama = nama;
        this.npm = npm;
        this.tipe = "mahasiswa";
        this.tanggalLahir = extractTanggalLahir(npm);
        this.jurusan = extractJurusan(npm);
    }

    // Getter for variables since it's private
    public long getNpm() {
        return this.npm;
    }

    public String getTanggalLahir() {
        return this.tanggalLahir;
    }

    public String getJurusan() {
        return this.jurusan;
    }

    public int getTotalMatkul() {
        return this.totalMatkul;
    }

    public MataKuliah[] getDaftarMataKuliah() {
        return this.daftarMataKuliah;
    }

    // New method matkulAda to check if certain mataKuliah existed
    public boolean matkulAda(MataKuliah mataKuliah) {
        if (totalMatkul == 0)
            return false;
        for (int i = 0; i < totalMatkul; i++) {
            if (daftarMataKuliah[i].equals(mataKuliah)) {
                return true;
            }
        }
        return false;
    }

    // New method getMataKuliah to get a MataKuliah from daftarMataKuliah array
    public MataKuliah getMataKuliah(String nama) {
        MataKuliah temp = daftarMataKuliah[0];
        for (int i = 0; i < 10; i++) {
            if (daftarMataKuliah[i].getNama().equals(nama)) {
                temp = daftarMataKuliah[i];
            }
        }
        return temp;
    }

    public void addMatkul(MataKuliah mataKuliah) {
        // addMatkul method to add a MataKuliah into daftarMataKuliah array
        // First check if mataKuliah already taken, if yes reject
        // Else check if kapasitas of that mataKuliah is on limit, if yes reject
        // Else, add mataKuliah into daftarMataKuliah array and add this Mahasiswa into that mataKuliah daftarMahasiswa array
        if (matkulAda(mataKuliah)) {
            System.out.println("[DITOLAK] " + mataKuliah + " telah diambil sebelumnya");
        } else if (mataKuliah.getDaftarMahasiswa().length == mataKuliah.getKapasitas()) {
            System.out.println("[DITOLAK] " + mataKuliah + " telah penuh kapasitasnya");
        } else {
            daftarMataKuliah[totalMatkul++] = mataKuliah;
            mataKuliah.addDaftarMahasiswa(this);
            System.out.println(nama + " berhasil menambahkan mata kuliah " + mataKuliah);
        }
    }

    public void dropMatkul(MataKuliah mataKuliah) {
        // dropMatkul method to remove a mataKuliah from daftarMataKuliah array
        // First check if mataKuliah already taken, if no reject
        // If yes, use what I called "delete elements from array routine" to delete a mataKuliah
        // Decrement totalMatkul and do delete this Mahasiswa from mataKuliah daftarMahasiswa array
        if (matkulAda(mataKuliah)) {
            for (int i = 0; i < totalMatkul; i++) {
                if (daftarMataKuliah[i] == mataKuliah) {
                    daftarMataKuliah[i] = null;
                }
            }
            MataKuliah[] temp = new MataKuliah[10];
            for (int i = 0, k = 0; i < totalMatkul; i++) {
                if (daftarMataKuliah[i] == null) {
                    continue;
                }
                temp[k++] = daftarMataKuliah[i];
            }
            daftarMataKuliah = temp;
            totalMatkul--;
            mataKuliah.dropDaftarMahasiswa(this);
            System.out.println(nama + " berhasil drop mata kuliah " + mataKuliah);
        } else {
            System.out.println("[DITOLAK] " + mataKuliah + " belum pernah diambil");
        }
    }

    public String extractTanggalLahir(long npm) {
        // extractTanggalLahir get TanggalLahir from NPM and used in the constructor as it's the part of the identity of Mahasiswa by concatenating it
        String npmConcat = Long.toString(npm).substring(4, 12);
        tanggalLahir = npmConcat.substring(0, 2) + "-" + npmConcat.substring(2, 4) + "-" + npmConcat.substring(4);
        return tanggalLahir;
    }

    public String extractJurusan(long npm) {
        // extractJurusan get Jurusan from NPM and used in the constructor as it's the part of the identity of Mahasiswa
        String npmConcat = Long.toString(npm).substring(2, 4);
        switch (npmConcat) {
            case "01":
                jurusan = "Ilmu Komputer";
                break;
            case "02":
                jurusan = "Sistem Informasi";
                break;
        }
        return jurusan;
    }

    // This is override method menyapa from ElemenFasilkom class, to accomodate if elemenFasilkom is Dosen object
    // The difference is add a conditional if elemenFasilkom is Dosen object, add this object and that Dosen object's friendship point by 5
    @Override
    public void menyapa(ElemenFasilkom elemenFasilkom) {
        if (sudahMenyapa(elemenFasilkom)) {
            System.out.println("[DITOLAK] " + nama + " telah menyapa " + elemenFasilkom + " hari ini");
        } else {
            telahMenyapa[jumlahMenyapa++] = elemenFasilkom;
            elemenFasilkom.telahMenyapa[elemenFasilkom.jumlahMenyapa++] = this;
            System.out.println(nama + " menyapa dengan " + elemenFasilkom);

            if (elemenFasilkom.getTipe().equals("dosen") && matkulAda(((Dosen) elemenFasilkom).getMataKuliah())) {
                this.setFriendship(5);
                elemenFasilkom.setFriendship(5);
            }
        }
    }
}