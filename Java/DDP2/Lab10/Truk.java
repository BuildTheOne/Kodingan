package DDP2.Lab10;

import java.util.*;

// TODO: Ubah class Truk menjadi sebuah class Generics
public class Truk<T> {

	// TODO: Tambahkan atribut ArrayList of Generics bernama daftarBarang
	// anda boleh menambahkan atribut lain bila perlu

	private String nama;
	// Tambahkan atribut ArrayList baru sesuai diagram
	public ArrayList<T> daftarBarang = new ArrayList<>();

	public Truk(String nama) {
		this.nama = nama;
	}

	// TODO: Tambahkan parameter ke method addBarang
	public void addBarang(T barang) {
		// TODO: Implementasi penambahan barang ke atribut daftarBarang
		//
		daftarBarang.add(barang);
	}

	public int getTotalBobot() {
		// TODO: Implementasi method getTotalBobot
		// Memakai foreach untuk mendapatkan bobot tiap barang kemudian ditotal
		int total = 0;
		for (T barang : daftarBarang) {
			total += ((Barang) barang).getBobot();
		}
		return total;
	}

	public void rekap() {
		// TODO: Implementasi method rekap
		// Memakai foreach untuk mendapatkan rekap() dari tiap barang
		// dan dapatkan total bobot dari semua barang dalam truk dengan getTotalBobot()
		if (daftarBarang.size() == 0) {
			System.out.println("Belum ada barang di " + nama);
		} else {
			System.out.println("Berikut rekap barang-barang dari " + nama);
			for (T barang : daftarBarang) {
				System.out.println(((Barang) barang).getRekap());
			}
			System.out.println("Total bobot barang di truk ini: " + getTotalBobot());
		}
	}

}