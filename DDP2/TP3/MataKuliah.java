package DDP2.TP3;

import java.util.Arrays;

class MataKuliah {
    // Change the modifier into private to secure the variables
    private String nama;
    private int kapasitas;
    private Dosen dosen;
    private Mahasiswa[] daftarMahasiswa;

    public MataKuliah(String nama, int kapasitas) {
        // Set the constructor with the nama, kapasitas, and initialize daftarMahasiswa array into empty array for easier implementation
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.daftarMahasiswa = new Mahasiswa[0];
    }

    // Getter for variables since it's private
    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public String getNama() {
        return this.nama;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public Dosen getDosen() {
        return this.dosen;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    // New addDaftarMahasiswa method is to add Mahasiswa into daftarMahasiswa array using "add the elements into array routine"
    // This method called in Mahasiswa class
    public void addDaftarMahasiswa(Mahasiswa mahasiswa) {
        Mahasiswa[] temp = Arrays.copyOf(daftarMahasiswa, daftarMahasiswa.length + 1);
        temp[daftarMahasiswa.length] = mahasiswa;
        daftarMahasiswa = temp;
    }

    // New dropDaftarMahasiswa method is to remove Mahasiswa into daftarMahasiswa array using "remove the elements into array routine"
    // This method called in Mahasiswa class
    public void dropDaftarMahasiswa(Mahasiswa mahasiswa) {
        Mahasiswa[] temp = new Mahasiswa[daftarMahasiswa.length - 1];
        for (int i = 0; i < getDaftarMahasiswa().length; i++) {
            if (daftarMahasiswa[i] == mahasiswa) {
                daftarMahasiswa[i] = null;
            }
        }

        for (int i = 0, k = 0; i < getDaftarMahasiswa().length; i++) {
            if (daftarMahasiswa[i] == null) {
                continue;
            }
            temp[k++] = daftarMahasiswa[i];
        }
        daftarMahasiswa = temp;
    }

    // So, for simpler implementation, I choose to just called the each object's respective method in methods below
    // In the Main Class, this methods are called instead, and then it will thrown to object's method
    public void addMahasiswa(Mahasiswa mahasiswa) {
        mahasiswa.addMatkul(this);
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        mahasiswa.dropMatkul(this);
    }

    public void addDosen(Dosen dosen) {
        dosen.mengajarMataKuliah(this);
    }

    public void dropDosen() {
        dosen.dropMataKuliah();
    }

    public String toString() {
        // toString return nama
        return nama;
    }
}