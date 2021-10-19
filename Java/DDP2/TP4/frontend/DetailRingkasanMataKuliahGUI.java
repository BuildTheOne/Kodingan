package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;

import DDP2.TP4.backend.*;

public class DetailRingkasanMataKuliahGUI extends JPanel {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel();
    JLabel titleLabel = new JLabel("Detail Ringkasan Mata Kuliah");
    JLabel namaLabel = new JLabel("Nama Mata Kuliah");
    JLabel namaField = new JLabel();
    JLabel kodeLabel = new JLabel("Kode");
    JLabel kodeField = new JLabel();
    JLabel sksLabel = new JLabel("SKS");
    JLabel sksField = new JLabel();
    JLabel jmlhMhsLabel = new JLabel("Jumlah Mahasiswa");
    JLabel jmlhMhsField = new JLabel();
    JLabel kapasitasLabel = new JLabel("Kapasitas");
    JLabel kapasitasField = new JLabel();
    JLabel daftarMhsLabel = new JLabel("Daftar Mahasiswa");
    JPanel daftarMhs = new JPanel();
    JButton btnBack = new JButton("Kembali");

    public DetailRingkasanMataKuliahGUI(JFrame frame, MataKuliah mataKuliah, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        frame.setLayout(new CardLayout());

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        namaField.setText(": " + mataKuliah.getNama());
        kodeField.setText(": " + mataKuliah.getKode());
        sksField.setText(": " + mataKuliah.getSKS());
        jmlhMhsField.setText(": " + mataKuliah.getJumlahMahasiswa());
        kapasitasField.setText(": " + mataKuliah.getKapasitas());

        daftarMhs.setLayout(new BoxLayout(daftarMhs, BoxLayout.Y_AXIS));
        listMahasiswa(frame, mataKuliah);

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
            .addComponent(kodeLabel)
            .addComponent(sksLabel)
            .addComponent(jmlhMhsLabel)
            .addComponent(kapasitasLabel)
            .addComponent(daftarMhsLabel)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(namaField)
            .addComponent(kodeField)
            .addComponent(sksField)
            .addComponent(jmlhMhsField)
            .addComponent(kapasitasField)
            .addComponent(daftarMhs)
            ))
        .addComponent(btnBack)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(titleLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(namaLabel)
            .addComponent(namaField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(kodeLabel)
            .addComponent(kodeField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(sksLabel)
            .addComponent(sksField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(jmlhMhsLabel)
            .addComponent(jmlhMhsField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(kapasitasLabel)
            .addComponent(kapasitasField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(daftarMhsLabel)
            .addComponent(daftarMhs))
        .addComponent(btnBack)
        );

        mainPanel.add(panel1, new GridBagConstraints());
        frame.add(mainPanel);
    }

    public void listMahasiswa(JFrame frame, MataKuliah mataKuliah) {
        if (mataKuliah.getJumlahMahasiswa() == 0) {
            daftarMhs.add(new JLabel("Belum ada mahasiswa yang mengambil mata kuliah ini"));
        } else {
            for (int i = 0; i < mataKuliah.getJumlahMahasiswa(); i++) {
                if (i == 0) {
                    daftarMhs.add(new JLabel(": " + (i + 1) + ". " + mataKuliah.getDaftarMahasiswa()[i].getNama()));
                } else {
                    daftarMhs.add(new JLabel("  " + (i + 1) + ". " + mataKuliah.getDaftarMahasiswa()[i].getNama()));
                }
            }
        }
    }
}
