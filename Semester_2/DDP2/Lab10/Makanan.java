package DDP2.Lab10;

public class Makanan extends Barang {

    String expiredDate;

    public Makanan(int bobot, String nama, String expiredDate) {
		super(bobot, nama);
        this.expiredDate = expiredDate;
	}

    public String getRekap() {
        return String.format(
            "Makanan %s\n" + 
            "Bobot: %d\n" + 
            "Kedaluwarsa: %s", 
            super.getNama(), super.getBobot(), this.expiredDate);
    }

}