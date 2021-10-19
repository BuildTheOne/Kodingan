package DDP2.Lab10;

public abstract class Barang {

	int bobot;

	String nama;

	public Barang(int bobot, String nama) {
		this.bobot = bobot;
		this.nama = nama;
	}

	public int getBobot() {
		return this.bobot;
	}

	public String getNama() {
		return this.nama;
	}

	public abstract String getRekap();

}