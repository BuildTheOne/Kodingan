package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;

import DDP2.TP4.backend.*;

public class SistemAkademik {
    public static void main(String[] args) {
        new SistemAkademikGUI();
    }
}

class SistemAkademikGUI extends JFrame {
    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<Mahasiswa>();
    private static ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<MataKuliah>();
    public static Font fontGeneral = new Font("Century Gothic", Font.PLAIN, 14);
    public static Font fontTitle = new Font("Century Gothic", Font.BOLD, 20);

    public SistemAkademikGUI() {
        // Membuat Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        // TODO: Tambahkan hal-hal lain yang diperlukan
        frame.setLayout(new CardLayout());
        frame.setTitle("Administrator - Sistem Akademik");
        frame.setLocationRelativeTo(null);                  // Center the frame

        new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
        frame.setVisible(true);
    }

    public static void addBtnListener(JFrame frame, JPanel currentJpnl, JPanel newJpnl) {
        frame.remove(currentJpnl);
        frame.add(newJpnl);
    }

    public static Mahasiswa getMahasiswa(long npm) {
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.getNpm() == npm) {
                return mahasiswa;
            }
        }
        return null;
    }

    public static MataKuliah getMataKuliah(String nama) {
        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.getNama().equals(nama)) {
                return mataKuliah;
            }
        }
        return null;
    }
}
