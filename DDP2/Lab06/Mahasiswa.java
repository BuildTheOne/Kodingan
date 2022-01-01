package DDP2.Lab06;

import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mahasiswa {
    private static final int KUNJUNGI = 1;
    private static final int BERAKTIVITAS = 2;
    private static final int SELESAI = 3;
    private static final int KELUAR = 4;
    private static final int DEBUG = 0;
    private String[] listAktivitasDilakukan = new String[0];
    private String[] listTempatDikunjungi = new String[0];

    private Scanner input = new Scanner(System.in);

    private void kunjungiTempat(String namaTempat) {
        System.out.println("\n--------------------------- BERKUNJUNG ---------------------------\n");
        Tempat t = Tempat.getTempat(namaTempat);
        if (t == null) {
            System.out.println("Maaf, tempat tersebut tidak ada di Fasilkom UI.");
            return;
        } else {
            this.tambahArrayListAktivitas(t.kunjungi());
            this.tambahArrayTempatDikunjungi(t.getNama());
            System.out.println(t.kunjungi() + "\n");
        }

        System.out.println("Apakah kamu ingin melakukan sesuatu disini?");
        System.out.println(
                "Jika kamu mengetik ya, maka program akan mencetak aktivitas yang bisa dilakukan di tempat tersebut,");
        System.out.println("dan kamu harus mengetik aktivitas tersebut untuk melakukannya.");
        System.out.println("Jika kamu mengetik yang lain, maka program akan kembali ke menu utama.\n");
        if (input.nextLine().equalsIgnoreCase("ya")) {
            switch (t.getClass().getSimpleName()) {
            case "Gedung":
                Gedung g = (Gedung) t;
                System.out.printf("Di Gedung, kamu bisa Belajar. List semua ruangan: \n%s\n",
                        Arrays.toString(g.listSemuaRuangan()));
                break;

            case "LabKomputer":
                System.out.println("Di LabKomputer, kamu bisa Gunakan Komputer.");
                break;

            default:
                break;
            }

            System.out.print("Apa yang ingin kamu lakukan? ");
            String aktivitas = input.nextLine();
            lakukanAktivitas(aktivitas, t.getNama());
        }
    }

    private void lakukanAktivitas(String aktivitas, String namaTempat) {
        System.out.println("\n-------------------------- BERAKTIVITAS --------------------------\n");
        Tempat t = Tempat.getTempat(namaTempat);
        if (t == null) {
            System.out.printf("Tidak ada tempat bernama %s\n", namaTempat);
            return;
        } else {
            this.tambahArrayListAktivitas(t.beraktivitas(aktivitas, t.getNama()));
            this.tambahArrayTempatDikunjungi(t.getNama());
            System.out.println(t.beraktivitas(aktivitas, t.getNama()));
        }
    }

    private void rekapKunjungan() {
        System.out.print("Melakukan Rekap Kunjungan...");
        System.out.println("Selama Virtual tour, kamu telah mengunjungi tempat berikut:");
        // TODO: Selesaikan bagian ini!
        // Using for loop to show the the content of array
        for (int i = 0; i < listTempatDikunjungi.length; i++) {
            System.out.println(listTempatDikunjungi[i]);
        }
    }

    private void rekapAktivitas() {
        System.out.print("Melakukan Rekap Aktivitas...");
        System.out.println("Selama Virtual tour, aktivitas yang telah kamu lakukan adalah:");
        // TODO: Selesaikan bagian ini!
        // Using for loop to show the the content of array
        for (int i = 0; i < listAktivitasDilakukan.length; i++) {
            System.out.println(listAktivitasDilakukan[i]);
        }
    }

    private void daftarMenu() {
        int pilihan = 0;
        String namaTempat = "";
        String aktivitas = "";
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----------------------------- MENU -------------------------------\n");
            System.out.println("Silakan pilih menu:");
            System.out.println("1. Kunjungi suatu Tempat");
            System.out.println("2. Lakukan sebuah aktivitas di Tempat tertentu");
            System.out.println("3. Selesaikan tur");
            System.out.println("4. Keluar");
            System.out.print("\nPilih: ");
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            System.out.println();
            if (pilihan == KUNJUNGI) {
                System.out.println("Mau berkunjung kemana? Jika ingin melihat semua tempat yang ada,");
                System.out.println("Ketik \"List Tempat\"");
                namaTempat = input.nextLine();
                if (namaTempat.equalsIgnoreCase("List Tempat")) {
                    for (Tempat t : Tempat.semuaTempat) {
                        System.out.println(t.toString() + "\n");
                    }
                } else {
                    kunjungiTempat(namaTempat);
                }
                namaTempat = "";
            } else if (pilihan == BERAKTIVITAS) {
                System.out.println("Mau beraktivitas apa, dan dimana? Format: Aktivitas;Nama Tempat");
                System.out.println("Jika ingin melihat apa saja yang bisa dilakukan di suatu tempat, ketik:");
                System.out.println("\"List Aktivitas\".");
                System.out.println("Ketik \"List Tempat\" untuk melihat semua tempat yang ada.");
                String perintah = input.nextLine();
                if (perintah.equals("List Tempat")) {
                    for (Tempat t : Tempat.semuaTempat) {
                        System.out.println(t.toString() + "\n");
                    }
                } else if (perintah.contains("List Aktivitas")) {
                    System.out.println("Di Gedung, kamu bisa Belajar <nama ruangan>.");
                    System.out.println("Di Perpustakaan, kamu bisa Baca Buku <nama buku>.");
                    System.out.println("Di LabKomputer, kamu bisa Gunakan Komputer.");
                    System.out.println("Di Kantin, kamu bisa Beli <nama makanan atau minuman>.");
                } else {
                    aktivitas = perintah.split(";")[0];
                    namaTempat = perintah.split(";")[1];
                    lakukanAktivitas(aktivitas, namaTempat);
                }
                aktivitas = "";
                namaTempat = "";
            } else if (pilihan == SELESAI) {
                rekapKunjungan();
                System.out.println();
                rekapAktivitas();
                System.out.println("\nVirtual Tournya selesai!");
                System.out.println("Sampai jumpa!");
                exit = true;
            } else if (pilihan == KELUAR) {
                System.out.println("\nVirtual Tournya selesai!");
                System.out.println("Sampai jumpa!");
                exit = true;
            } else if (pilihan == DEBUG) {
                System.out.println("=================DEBUGGING=================");
                System.out.println("Tempat yang berhasil didaftarkan:");
                for (Tempat t : Tempat.semuaTempat) {
                    System.out.println(t.toString());
                }
                System.out.println("=================DEBUGGING=================");
            }
        }
    }

    private void run() {
        System.out.println("====================== Virtual Tour Fasilkom UI =======================\n");
        System.out.println("Selamat datang di Aplikasi Virtual Tour Fasilkom UI!");
        System.out.println("Sedang mengisi Database dengan data dari file denah-fasilkom.txt");
        parseData();
        System.out.println("Selesai mengisi Database. Selamat menikmati Virtual Tournya!");

        daftarMenu();
        input.close();
    }

    public static void main(String[] args) {
        Mahasiswa mhs = new Mahasiswa();
        mhs.run();
    }

    private void tambahArrayListAktivitas(String value) {
        String[] temp = Arrays.copyOf(this.listAktivitasDilakukan, this.listAktivitasDilakukan.length + 1);
        temp[this.listAktivitasDilakukan.length] = value;
        this.listAktivitasDilakukan = temp;
    }

    private void tambahArrayTempatDikunjungi(String value) {
        String[] temp = Arrays.copyOf(this.listTempatDikunjungi, this.listTempatDikunjungi.length + 1);
        temp[this.listTempatDikunjungi.length] = value;
        this.listTempatDikunjungi = temp;
    }

    private void parseData() {
        try {
            Scanner reader = new Scanner(new File("C:\\Users\\DIMAS\\DIMAS\\Coding\\Java\\DDP2\\Lab06\\denah-fasilkom.txt"));
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] temp = null;
                if (data.contains("number")) {
                    int jumlah = Integer.parseInt(data.split(" ")[2]);
                    if (data.contains("gedung")) {
                        for (int i = 0; i < jumlah; i++) {
                            temp = reader.nextLine().split(";");
                            // Urutan: Nama, Lokasi, deskripsi
                            Gedung gedung = new Gedung(temp[0], temp[1], temp[2]);
                            Tempat.tambahArrayTempat(gedung);
                        }
                    } else if (data.contains("labkom")) {
                        for (int i = 0; i < jumlah; i++) {
                            temp = reader.nextLine().split(";");
                            // Urutan: Nama, Lokasi persis, jumlah komputer, deskripsi
                            LabKomputer labkom = new LabKomputer(temp[0], temp[2], temp[4], Integer.parseInt(temp[3]));
                            Tempat.tambahArrayTempat(labkom);
                        }
                    }
                } else if (data.contains("activitynum")) {
                    String namaTempat = data.split(";")[1];
                    int jumlah = Integer.parseInt(data.split(";")[2]);
                    Tempat tempat = Tempat.getTempat(namaTempat);
                    for (int i = 0; i < jumlah; i++) {
                        String aktivitas = reader.nextLine();
                        Gedung g = (Gedung) tempat;
                        g.tambahRuangan(aktivitas);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(
                    "File denah-fasilkom.txt tidak ditemukan, harap cek keberadaan filenya lalu jalankan ulang.");
        }

    }
}
