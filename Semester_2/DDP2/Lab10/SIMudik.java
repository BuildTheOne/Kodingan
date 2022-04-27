package DDP2.Lab10;

import java.util.Scanner;

public class SIMudik {

	private static final int TAMBAH_BARANG = 1;
	private static final int REKAP = 2;
	private static final int KELUAR = 3; 

	private Truk<Makanan> trukMakanan;
	private Truk<Pakaian> trukPakaian;
	private Truk<Elektronik> trukElektronik;

	private Scanner in;

	public SIMudik() {
		trukMakanan = new Truk<Makanan>("Truk Makanan");
		trukPakaian = new Truk<Pakaian>("Truk Pakaian");
		trukElektronik = new Truk<Elektronik>("Truk Elektronik");

		in = new Scanner(System.in);
	}

	public void addMakanan() {
		System.out.print("Masukkan nama makanan: ");
		String nama = in.nextLine();
		System.out.print("Masukkan bobot (dalam gram): ");
		int bobot = Integer.parseInt(in.nextLine());
		System.out.print("Masukkan tanggal kedaluwarsa: ");
		String expiredDate = in.nextLine();

		// TODO: Buat object makanan dan tambahkan ke truk yang sesuai
		// Gunakan method addBarang dari class Barang dan argumen diisi dengan inisialisasi object Makanan baru
		trukMakanan.addBarang(new Makanan(bobot, nama, expiredDate));

		System.out.println("Makanan " + nama + " ditambahkan ke truk makanan!\n");
	}

	public void addPakaian() {
		System.out.print("Masukkan nama pakaian: ");
		String nama = in.nextLine();
		System.out.print("Masukkan bobot (dalam gram): ");
		int bobot = Integer.parseInt(in.nextLine());
		System.out.print("Masukkan warna: ");
		String warna = in.nextLine();
		System.out.print("Masukkan ukuran (S/M/L): ");
		char ukuran = in.nextLine().charAt(0);

		// TODO: Buat object Pakaian dan tambahkan ke truk yang sesuai
		// Gunakan method addBarang dari class Barang dan argumen diisi dengan inisialisasi object Pakaian baru
		trukPakaian.addBarang(new Pakaian(bobot, nama, warna, ukuran));

		System.out.println("Pakaian " + nama + " ditambahkan ke truk pakaian!\n");
	}

	public void addElektronik() {
		System.out.print("Masukkan nama barang elektronik: ");
		String nama = in.nextLine();
		System.out.print("Masukkan bobot (dalam gram): ");
		int bobot = Integer.parseInt(in.nextLine());
		System.out.print("Deskripsikan kondisi saat ini: ");
		String kondisi = in.nextLine();

		// TODO: Buat object Elektronik dan tambahkan ke truk yang sesuai
		// Gunakan method addBarang dari class Barang dan argumen diisi dengan inisialisasi object Elektronik baru
		trukElektronik.addBarang(new Elektronik(bobot, nama, kondisi));

		System.out.println("Barang elektronik " + nama + " ditambahkan ke truk elektronik!\n");
	}

	public void run() {
		System.out.println("----- Selamat Datang di Sistem Informasi Mudik -----");
		System.out.println("Masukkan perintah 1 untuk menambah barang");
		System.out.println("Masukkan perintah 2 untuk melihat rekap dari sebuah truk");
		System.out.println("Masukkan perintah 3 untuk keluar dari sistem");
		System.out.println("");

		while (true) {
			System.out.print("Masukkan perintah: ");
			int perintah = Integer.parseInt(in.nextLine());
			
			if (perintah == TAMBAH_BARANG) {

				System.out.print("Masukkan jenis barang (Makanan / Pakaian / Elektronik): ");
				String jenis = in.nextLine();

				if (jenis.equalsIgnoreCase("Makanan")) {
					addMakanan();
				} else if (jenis.equalsIgnoreCase("Pakaian")) {
					addPakaian();
				} else if (jenis.equalsIgnoreCase("Elektronik")) {
					addElektronik();
				} else {
					System.out.println("Tidak boleh membawa barang selain makanan, pakaian, atau elektronik");
				}

			} else if (perintah == REKAP) {

				System.out.print("Masukkan jenis truk (Makanan / Pakaian / Elektronik): ");
				String jenis = in.nextLine();

				if (jenis.equalsIgnoreCase("Makanan")) {
					trukMakanan.rekap();
				} else if (jenis.equalsIgnoreCase("Pakaian")) {
					trukPakaian.rekap();
				} else if (jenis.equalsIgnoreCase("Elektronik")) {
					trukElektronik.rekap();
				} else {
					System.out.println("Truk tidak ditemukan");
				}

			} else if (perintah == KELUAR) {
				System.out.println("Terima kasih telah menggunakan Sistem Informasi Mudik!");
				return ;
			}
		}
	}

	public static void main(String[] args) {
		SIMudik sistem = new SIMudik();
		sistem.run();
	}

}