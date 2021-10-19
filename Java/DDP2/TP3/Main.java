package DDP2.TP3;

import java.util.*;

public class Main {
    // Change all the variables into static so it created when the object Main is created
    private static ElemenFasilkom[] daftarElemenFasilkom = new ElemenFasilkom[100];
    private static MataKuliah[] daftarMataKuliah = new MataKuliah[100];
    private static int totalElemenFasilkom = 0;
    private static int totalMataKuliah = 0;

    public static void addMahasiswa(String nama, long npm) {
        // addMahasiswa method add a new Mahasiswa object into daftarElemenFasilkomarray and increment totalElemenFasilkom
        daftarElemenFasilkom[totalElemenFasilkom++] = new Mahasiswa(nama, npm);
        System.out.println(nama + " berhasil ditambahkan");
    }

    public static void addDosen(String nama) {
        // addDosen method add a new Dosen object into daftarElemenFasilkom array and increment totalElemenFasilkom
        daftarElemenFasilkom[totalElemenFasilkom++] = new Dosen(nama);
        System.out.println(nama + " berhasil ditambahkan");
    }

    public static void addElemenKantin(String nama) {
        // addElemenKantin method add a new ElemenKantin object into daftarElemenFasilkom array and increment totalElemenFasilkom
        daftarElemenFasilkom[totalElemenFasilkom++] = new ElemenKantin(nama);
        System.out.println(nama + " berhasil ditambahkan");
    }

    // This is a new method to find if an element by name is exist in daftarElemenFasilkom array
    // Because all the input would always valid (from the daftarElemenFasilkom array), there is no need to implement if the element doesn't exist
    public static ElemenFasilkom dapatkanElemen(String nama) {
        ElemenFasilkom temp = daftarElemenFasilkom[0];
        for (int i = 0; i < totalElemenFasilkom; i++) {
            if (daftarElemenFasilkom[i].getNama().equals(nama))
                return daftarElemenFasilkom[i];
        }
        return temp;
    }

    // This is a new method to find if a Matkul by name is exist in daftarMataKuliah array
    // Similarly with dapatkanElemen, there is no need to implement if matkul doesn't exist
    public static MataKuliah dapatkanMatkul(String nama) {
        MataKuliah temp = daftarMataKuliah[0];
        for (int i = 0; i < totalMataKuliah; i++) {
            if (daftarMataKuliah[i].getNama().equals(nama))
                return daftarMataKuliah[i];
        }
        return temp;
    }

    public static void menyapa(String objek1, String objek2) {
        // menyapa method is used if two elements are menyapa each other
        // First, get elements from both objects with dapatkanElemen
        // Then checked if the elements are equal, if yes print the error message, else menyapa
        ElemenFasilkom elemen1 = dapatkanElemen(objek1);
        ElemenFasilkom elemen2 = dapatkanElemen(objek2);
        if (elemen1.equals(elemen2)) {
            System.out.println("[DITOLAK] Objek yang sama tidak bisa saling menyapa");
        } else {
            elemen1.menyapa(elemen2);
        }
    }

    public static void addMakanan(String objek, String namaMakanan, long harga) {
        // addMakanan method is used to addMakanan to an elemenKantin object
        // First get the element, then check if the element are elemenKantin, if yes add the Makanan, else reject
        ElemenFasilkom elemen = dapatkanElemen(objek);
        if (elemen.getTipe().equals("elemenKantin")) {
            ((ElemenKantin) elemen).setMakanan(namaMakanan, harga);
        } else {
            System.out.println("[DITOLAK] " + objek + " bukan merupakan elemen kantin");
        }
    }

    public static void membeliMakanan(String objek1, String objek2, String namaMakanan) {
        // membeliMakanan method is used if objek1 want to membeli makanan object from objek2
        // First get the elements from both objects, then check if objek2 is elemenKantin object
        // If yes, check if objek1 and objek2 is the same element, if yes reject, else objek1 membeli objek2
        ElemenFasilkom pembeli = dapatkanElemen(objek1);
        ElemenFasilkom penjual = dapatkanElemen(objek2);
        if (penjual.getTipe().equals("elemenKantin")) {
            if (penjual.equals(pembeli)) {
                System.out.println("[DITOLAK] Elemen kantin tidak bisa membeli makanan sendiri");
            } else {
                pembeli.membeliMakanan(pembeli, penjual, namaMakanan);
            }
        } else {
            System.out.println("[DITOLAK] Hanya elemen kantin yang dapat menjual makanan");
        }
    }

    public static void createMatkul(String nama, int kapasitas) {
        // createMatkul method add a new MataKuliah object into daftarMataKuliah array and increment totalMataKuliah
        daftarMataKuliah[totalMataKuliah++] = new MataKuliah(nama, kapasitas);
        System.out.println(nama + " berhasil ditambahkan dengan kapasitas " + kapasitas);
    }

    public static void addMatkul(String objek, String namaMataKuliah) {
        // addMatkul method is to add a Matkul to a Mahasiswa object
        // First get the element and Matkul, then check if the element is Mahasiswa object
        // If yes, add Matkul, else reject
        ElemenFasilkom elemen = dapatkanElemen(objek);
        MataKuliah matkul = dapatkanMatkul(namaMataKuliah);
        if (elemen.getTipe().equals("mahasiswa")) {
            matkul.addMahasiswa((Mahasiswa) elemen);
        } else {
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat menambahkan matkul");
        }
    }

    public static void dropMatkul(String objek, String namaMataKuliah) {
        // dropMatkul method delete a Matkul from a Mahasiswa object
        // First get the element and Matkul, then check if the element is Mahasiswa object
        // If yes, drop Matkul, else reject
        ElemenFasilkom elemen = dapatkanElemen(objek);
        MataKuliah matkul = dapatkanMatkul(namaMataKuliah);
        if (elemen.getTipe().equals("mahasiswa")) {
            matkul.dropMahasiswa((Mahasiswa) elemen);
        } else {
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat drop matkul");
        }
    }

    public static void mengajarMatkul(String objek, String namaMataKuliah) {
        // mengajarMatkul method add Matkul to a Dosen object
        // First get the element and Matkul, then check if the element is Dosen object
        // If yes, add Matkul, else reject
        ElemenFasilkom elemen = dapatkanElemen(objek);
        MataKuliah matkul = dapatkanMatkul(namaMataKuliah);
        if (elemen.getTipe().equals("dosen")) {
            matkul.addDosen((Dosen) elemen);
        } else {
            System.out.println("[DITOLAK] Hanya dosen yang dapat mengajar matkul");
        }
    }

    public static void berhentiMengajar(String objek) {
        // berhentiMengajar method delete Matkul to a Dosen object
        // First get the element and Matkul, then check if the element is Dosen object
        // If yes, delete Matkul, else reject
        ElemenFasilkom elemen = dapatkanElemen(objek);
        if (elemen.getTipe().equals("dosen")) {
            ((Dosen) elemen).dropMataKuliah();
        } else {
            System.out.println("[DITOLAK] Hanya dosen yang dapat berhenti mengajar");
        }
    }

    public static void ringkasanMahasiswa(String objek) {
        // ringkasanMahasiswa show detailed info from a Mahasiswa object
        // First get the element and checked if the element is Mahasiswa object
        // If yes, show info, else reject
        ElemenFasilkom elemen = dapatkanElemen(objek);
        if (elemen instanceof Mahasiswa) {
            Mahasiswa mhs = (Mahasiswa) elemen;                             // Casting the element into Mahasiswa
            System.out.println("Nama: " + objek);                           // Get nama
            System.out.println("Tanggal lahir: " + mhs.getTanggalLahir());  // get Tanggal Lahir
            System.out.println("Jurusan: " + mhs.getJurusan());             // get Jurusan

            System.out.println("Daftar Mata Kuliah: ");
            // Then checked if Mahasiswa has taken any MataKuliah
            // If yes, print the matkul using for loop, else print the error messages
            if (mhs.getTotalMatkul() == 0) {
                for (int i = 0; i < mhs.getTotalMatkul(); i++) {
                    System.out.println((i + 1) + ". " + mhs.getDaftarMataKuliah()[i]);
                }
            } else {
                System.out.println("Belum ada mata kuliah yang diambil");
            }
        } else {
            System.out.println("[DITOLAK] " + objek + " bukan merupakan seorang mahasiswa");
        }
    }

    public static void ringkasanMataKuliah(String namaMataKuliah) {
        // ringkasanMataKuliah method show detailed info from a MataKuliah
        MataKuliah matkul = dapatkanMatkul(namaMataKuliah);                             // get the Matkul
        System.out.println("Nama mata kuliah: " + namaMataKuliah);                      // get Nama
        System.out.println("Jumlah Mahasiswa: " + matkul.getDaftarMahasiswa().length);  // get total of Mahasiswa that take this MataKuliah
        System.out.println("Kapasitas: " + matkul.getKapasitas());                      // get Kapasitas of this MataKuliah

        // Use ternary operator to get the dosen, if any
        String dosen = matkul.getDosen() == null ? "Belum ada" : matkul.getDosen().getNama();
        System.out.println("Dosen pengajar: " + dosen);

        // Checked if there any Mahasiswa that taken this MataKuliah
        System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini: ");
        if (matkul.getDaftarMahasiswa().length == 0) {
            System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini");
        } else {
            for (int i = 0; i < matkul.getDaftarMahasiswa().length; i++) {
                System.out.println((i + 1) + ". " + matkul.getDaftarMahasiswa()[i]);
            }
        }
    }

    public static void nextDay() {
        // nextDay method is to add friendship point for all the element based on rules from the docs
        for (int i = 0; i < totalElemenFasilkom; i++) {
            // Get the element
            ElemenFasilkom elemen = daftarElemenFasilkom[i];
            // Check if element menyapa greater than or equal to total Element, add 10 point
            // Else, minus 5 point
            // Also, added the validation that the point would be 0 < point < 100
            if (elemen.jumlahMenyapa >= ((totalElemenFasilkom - 1) / 2)) {
                if (elemen.getFriendship() + 10 <= 100) {
                    elemen.setFriendship(10);
                } else {
                    elemen.friendship = 100;
                }
            } else {
                if (elemen.getFriendship() - 5 >= 0) {
                    elemen.setFriendship(-5);
                } else {
                    elemen.friendship = 0;
                }
            }
            // Then, reset Menyapa for each element
            elemen.resetMenyapa();
        }
        // Then, show the total point
        System.out.println("Hari ini 'kan berakhir. Kusimpulkan hari ini sebagai mentari senja yang indah");
        friendshipRanking();
    }

    public static void friendshipRanking() {
        // friendshipRanking method is to show the elements and sort it by friendship point
        // Copy the array so it would affect the original array
        ElemenFasilkom[] elements = (Arrays.copyOf(daftarElemenFasilkom, totalElemenFasilkom));
        // Use external library java.util.Comparator to, first, sort elements by name
        Arrays.sort(elements, Comparator.comparing(ElemenFasilkom::getNama));
        // Then, use Comparator again to sort elements by friendship point
        Arrays.sort(elements, Comparator.comparingInt(e -> ((ElemenFasilkom) e).getFriendship()).reversed());
        // Then use for loop to show the array
        for (int i = 0; i < totalElemenFasilkom; i++) {
            System.out.println((i + 1) + ". " + elements[i] + " (" + elements[i].getFriendship() + ")");
        }
    }

    public static void programEnd() {
        // Show the final point for all the elements
        System.out.println("Program telah berakhir. Berikut nilai terakhir dari friendship pada Fasilkom :");
        friendshipRanking();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            String in = input.nextLine();
            if (in.split(" ")[0].equals("ADD_MAHASISWA")) {
                addMahasiswa(in.split(" ")[1], Long.parseLong(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_DOSEN")) {
                addDosen(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("ADD_ELEMEN_KANTIN")) {
                addElemenKantin(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("MENYAPA")) {
                menyapa(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("ADD_MAKANAN")) {
                addMakanan(in.split(" ")[1], in.split(" ")[2], Long.parseLong(in.split(" ")[3]));
            } else if (in.split(" ")[0].equals("MEMBELI_MAKANAN")) {
                membeliMakanan(in.split(" ")[1], in.split(" ")[2], in.split(" ")[3]);
            } else if (in.split(" ")[0].equals("CREATE_MATKUL")) {
                createMatkul(in.split(" ")[1], Integer.parseInt(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_MATKUL")) {
                addMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("DROP_MATKUL")) {
                dropMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("MENGAJAR_MATKUL")) {
                mengajarMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("BERHENTI_MENGAJAR")) {
                berhentiMengajar(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MAHASISWA")) {
                ringkasanMahasiswa(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MATKUL")) {
                ringkasanMataKuliah(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("NEXT_DAY")) {
                nextDay();
            } else if (in.split(" ")[0].equals("PROGRAM_END")) {
                programEnd();
                break;
            }
        }
        input.close();
    }
}