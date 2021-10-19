package DDP2.TP2;

import java.util.Arrays;

public class Mahasiswa {
    private MataKuliah[] mataKuliah = new MataKuliah[10];
    private String[] masalahIRS;
    private int totalSKS;
    private String nama;
    private String jurusan;
    private long npm;

    public Mahasiswa(String nama, long npm) {
        /* TODO: implementasikan kode Anda di sini✓ */
        this.nama = nama;
        this.npm = npm;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getNpm() {
        return this.npm;
    }

    public void setNpm(long npm) {
        this.npm = npm;
    }

    public String getJurusan() {
        setJurusan();
        return this.jurusan;
    }

    public void setJurusan() {
        String npmCodeForJurusan = Long.toString(this.npm).substring(2, 4);
        switch (npmCodeForJurusan) {
        case "01":
            this.jurusan = "Ilmu Komputer";
            break;
        case "02":
            this.jurusan = "Sistem Informasi";
            break;
        }
    }

    public MataKuliah[] getMataKuliah() {
        return this.mataKuliah;
    }

    public void setMataKuliah(MataKuliah[] mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String[] getMasalahIRS() {
        return this.masalahIRS;
    }

    public void setMasalahIRS(String[] masalahIRS) {
        this.masalahIRS = masalahIRS;
    }

    public int getTotalSKS() {
        setTotalSKS();
        return this.totalSKS;
    }

    public void setTotalSKS() {
        totalSKS = 0;
        for (int i = 0; i < this.getJumlahMataKuliah(); i++) {
            if (mataKuliah[i] != null) {
                this.totalSKS += mataKuliah[i].getSks();
            }
        }
    }

    public int getJumlahMataKuliah() {
        int countMatkul = 0;
        for (int i = 0; i < 10; i++) {
            if (this.mataKuliah[i] == null) {
                break;
            } else {
                countMatkul++;
            }
        }
        return countMatkul;
    }

    public void addMatkul(MataKuliah mataKuliah) {
        /* TODO: implementasikan kode Anda di sini */

        if (mataKuliah.getDaftarMahasiswa() == null) {
            if (this.getJumlahMataKuliah() == 10) {
                System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
            } else {
                this.mataKuliah[this.getJumlahMataKuliah()] = mataKuliah;
                mataKuliah.addMahasiswa(this);
            }
        } else {
            boolean exist = false;
            for (int i = 0; i < mataKuliah.getDaftarMahasiswa().length;) {
                if (mataKuliah.getDaftarMahasiswa()[i].equals(this))
                    exist = true;
                break;
            }
            if (exist) {
                System.out.println("[DITOLAK] " + mataKuliah + " telah diambil sebelumnya.");
            } else if (mataKuliah.getKapasitas() == mataKuliah.getDaftarMahasiswa().length) {
                System.out.println("[DITOLAK] " + mataKuliah + " telah penuh kapasitasnya.");
            } else if (this.getJumlahMataKuliah() == 10) {
                System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
            } else {
                this.mataKuliah[getJumlahMataKuliah()] = mataKuliah;
                mataKuliah.addMahasiswa(this);
            }
        }
    }

    public void dropMatkul(MataKuliah mataKuliah) {
        /* TODO: implementasikan kode Anda di sini */

        boolean notExist = true;
        for (int i = 0; i < 10; i++) {
            if (this.mataKuliah[i] != null) {
                if (this.mataKuliah[i].equals(mataKuliah)) {
                    notExist = false;
                    this.mataKuliah[i] = null;
                    break;
                }
            }
        }

        if (notExist) {
            System.out.println("[DITOLAK] " + mataKuliah + " belum pernah diambil.");
        } else {
            MataKuliah[] temp = new MataKuliah[10];
            for (int i = 0, k = 0; i < 10; i++) {
                if (this.mataKuliah[i] == null) {
                    continue;
                }
                temp[k++] = this.mataKuliah[i];
            }
            this.mataKuliah = temp;
            mataKuliah.dropMahasiswa(this);
        }
    }

    public void cekIRS() {
        /* TODO: implementasikan kode Anda di sini */
        // Todo: 1. Cek kecocokan kode matkul dengan jurusan; 2. Cek total sks
        this.masalahIRS = new String[0];

        if (this.getTotalSKS() > 24) {
            this.masalahIRS = Arrays.copyOf(this.masalahIRS, this.masalahIRS.length + 1);
            this.masalahIRS[this.masalahIRS.length - 1] = "SKS yang Anda ambil lebih dari 24";
        }

        for (int i = 0; i < this.getJumlahMataKuliah(); i++) {
            String jurusan = this.getJurusan();
            MataKuliah matkul = this.mataKuliah[i];
            if ((jurusan.equals("Ilmu Komputer") && matkul.getKode().equals("SI"))
                    || jurusan.equals("Sistem Informasi") && matkul.getKode().equals("IK")) {
                String[] temp = Arrays.copyOf(this.masalahIRS, this.masalahIRS.length + 1);
                if (jurusan.equals("Ilmu Komputer")) {
                    temp[temp.length - 1] = ("Mata Kuliah " + matkul.getNama() + " tidak dapat diambil jurusan IK");
                } else {
                    temp[temp.length - 1] = ("Mata Kuliah " + matkul.getNama() + " tidak dapat diambil jurusan SI");
                }
                this.masalahIRS = temp;
            }
        }

        if (this.masalahIRS.length != 0) {
            for (int i = 1; i <= this.masalahIRS.length; i++) {
                System.out.println(i + ". " + masalahIRS[i - 1]);
            }
        } else {
            System.out.println("IRS tidak bermasalah.");
        }
    }

    public String toString() {
        /* TODO: implementasikan kode Anda di sini */
        return getNama();
    }

}
