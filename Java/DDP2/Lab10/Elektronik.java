package DDP2.Lab10;

public class Elektronik extends Barang {

    private String kondisi;

	public Elektronik(int bobot, String nama, String kondisi) {
		super(bobot, nama);
        this.kondisi = kondisi;
	}

    public String getRekap() {
        return String.format(
            "Barang Elektronik %s\n" + 
            "Bobot: %d\n" + 
            "Kondisi: %s", 
            super.getNama(), super.getBobot(), this.kondisi);
    }

}