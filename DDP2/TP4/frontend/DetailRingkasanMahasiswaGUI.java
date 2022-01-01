package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;

import DDP2.TP4.backend.*;

public class DetailRingkasanMahasiswaGUI extends JPanel {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel();
    JLabel titleLabel = new JLabel("Detail Ringkasan Mahasiswa");
    JLabel namaLabel = new JLabel("Nama");
    JLabel namaField = new JLabel();
    JLabel npmLabel = new JLabel("NPM");
    JLabel npmField = new JLabel();
    JLabel jurusanLabel = new JLabel("Jurusan");
    JLabel jurusanField = new JLabel();
    JLabel matkulLabel = new JLabel("Daftar Mata Kuliah");
    JPanel matkulPanel = new JPanel();
    JLabel sksLabel = new JLabel("Total SKS");
    JLabel sksField = new JLabel();
    JLabel irsLabel = new JLabel("Hasil Pengecekan IRS");
    JPanel irsPanel = new JPanel();
    JButton btnBack = new JButton("Kembali");

    public DetailRingkasanMahasiswaGUI(JFrame frame, Mahasiswa mahasiswa, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        frame.setLayout(new CardLayout());

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        namaField.setText(": " + mahasiswa.getNama());
        npmField.setText(": " + mahasiswa.getNpm());
        jurusanField.setText(": " + mahasiswa.getJurusan());
        matkulPanel.setLayout(new BoxLayout(matkulPanel, BoxLayout.Y_AXIS));
        listMatkul(frame, mahasiswa);
        sksField.setText(": " + mahasiswa.getTotalSKS());
        irsPanel.setLayout(new BoxLayout(irsPanel, BoxLayout.Y_AXIS));
        listIRS(frame, mahasiswa);

        btnBack.addActionListener(e -> SistemAkademikGUI.addBtnListener(frame, mainPanel, new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah)));

        GroupLayout layout = new GroupLayout(panel1);
        panel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(titleLabel)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(namaLabel)
            .addComponent(npmLabel)
            .addComponent(jurusanLabel)
            .addComponent(matkulLabel)
            .addComponent(sksLabel)
            .addComponent(irsLabel)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(namaField)
            .addComponent(npmField)
            .addComponent(jurusanField)
            .addComponent(matkulPanel)
            .addComponent(sksField)
            .addComponent(irsPanel)
            ))
        .addComponent(btnBack)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(titleLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(namaLabel)
            .addComponent(namaField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(npmLabel)
            .addComponent(npmField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(jurusanLabel)
            .addComponent(jurusanField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(matkulLabel)
            .addComponent(matkulPanel))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(sksLabel)
            .addComponent(sksField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(irsLabel)
            .addComponent(irsPanel))
        .addComponent(btnBack)
        );

        mainPanel.add(panel1, new GridBagConstraints());
        frame.add(mainPanel);
    }

    public void listMatkul(JFrame frame, Mahasiswa mahasiswa) {
        if (mahasiswa.getBanyakMatkul() == 0) {
            matkulPanel.add(new JLabel("Belum ada mata kuliah yang diambil"));
        } else {
            for (int i = 0; i < mahasiswa.getBanyakMatkul(); i++) {
                matkulPanel.add(new JLabel((i + 1) + ". " + mahasiswa.getMataKuliah()[i]));
            }
        }
    }

    public void listIRS(JFrame frame, Mahasiswa mahasiswa) {
        mahasiswa.cekIRS();
        if (mahasiswa.getBanyakMasalahIRS() == 0) {
            irsPanel.add(new JLabel(": IRS tidak bermasalah"));
        } else {
            for (int i = 0; i < mahasiswa.getBanyakMasalahIRS(); i++) {
                if (i == 0) {
                    irsPanel.add(new JLabel(": " + (i + 1) + ". " + mahasiswa.getMasalahIRS()[i]));
                } else {
                    irsPanel.add(new JLabel("  " + (i + 1) + ". " + mahasiswa.getMasalahIRS()[i]));
                }
            }
        }
    }
}
