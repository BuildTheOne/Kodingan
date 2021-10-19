package DDP2.TP2;

import java.util.Arrays;

public class MataKuliah {
    private String kode;
    private String nama;
    private int sks;
    private int kapasitas;
    private Mahasiswa[] daftarMahasiswa;

    public MataKuliah(String kode, String nama, int sks, int kapasitas) {
        /* TODO: implementasikan kode Anda di sini✓ */
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.kapasitas = kapasitas;
    }

    public String getKode() {
        return this.kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSks() {
        return this.sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public void setDaftarMahasiswa(Mahasiswa[] daftarMahasiswa) {
        this.daftarMahasiswa = daftarMahasiswa;
    }

    public int getJumlahMahasiswa() {
        if (this.getDaftarMahasiswa() == null) {
            return 0;
        } else {
            return this.getDaftarMahasiswa().length;
        }
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        /* TODO: implementasikan kode Anda di sini */

        if (this.daftarMahasiswa == null) {
            this.daftarMahasiswa = new Mahasiswa[0];
        }

        Mahasiswa[] temp = Arrays.copyOf(getDaftarMahasiswa(), getDaftarMahasiswa().length + 1);
        temp[temp.length - 1] = mahasiswa;
        this.daftarMahasiswa = temp;
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        /* TODO: implementasikan kode Anda di sini */

        for (int i = 0; i < this.daftarMahasiswa.length; i++) {
            if (this.daftarMahasiswa[i].equals(mahasiswa)) {
                this.daftarMahasiswa[i] = null;
                break;
            }
        }

        Mahasiswa[] temp1 = Arrays.copyOf(this.daftarMahasiswa, this.daftarMahasiswa.length);
        Mahasiswa[] temp2 = Arrays.copyOf(this.daftarMahasiswa, this.daftarMahasiswa.length - 1);
        for (int i = 0, k = 0; i < temp1.length; i++) {
            if (temp1[i] == null) {
                continue;
            }
            temp2[k++] = temp1[i];
        }
        this.daftarMahasiswa = temp2;
    }

    public void cekMahasiswa() {
        /* TODO: implementasikan kode Anda di sini */
        if (this.getDaftarMahasiswa() == null || this.getDaftarMahasiswa().length == 0) {
            System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini.");
        } else {
            for (int i = 1; i <= this.getDaftarMahasiswa().length; i++) {
                System.out.println(i + ". " + this.getDaftarMahasiswa()[i - 1]);
            }
        }
    }

    public String toString() {
        /* TODO: implementasikan kode Anda di sini */
        return getNama();
    }
}
