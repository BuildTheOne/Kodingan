package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import DDP2.TP4.backend.*;

public class RingkasanMataKuliahGUI extends JPanel {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel();
    JLabel titleLabel = new JLabel("Ringkasan Mata Kuliah");
    JLabel labelMatkul = new JLabel("Nama Mata Kuliah");
    JComboBox<MataKuliah> matkulOption;
    JButton findMatkulBtn = new JButton("Cari");
    JButton btnBack = new JButton("Kembali");

    public RingkasanMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        frame.setLayout(new CardLayout());

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        Collections.sort(daftarMataKuliah);
        MataKuliah[] matkulList = daftarMataKuliah.toArray(new MataKuliah[0]);
        matkulOption = new JComboBox<MataKuliah>(matkulList);

        findMatkulBtn.addActionListener(e -> findMatkul(frame, mainPanel, daftarMahasiswa, daftarMataKuliah));
        btnBack.addActionListener(e -> SistemAkademikGUI.addBtnListener(frame, mainPanel, new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah)));

        GroupLayout layout = new GroupLayout(panel1);
        panel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(titleLabel)
        .addGroup(layout.createSequentialGroup()
            .addComponent(labelMatkul)
            .addComponent(matkulOption))
        .addGroup(layout.createSequentialGroup()
            .addComponent(findMatkulBtn)
            .addComponent(btnBack))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(titleLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelMatkul)
            .addComponent(matkulOption))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(findMatkulBtn)
            .addComponent(btnBack))
        );

        mainPanel.add(panel1, new GridBagConstraints());
        frame.add(mainPanel);
    }

    public void findMatkul(JFrame frame, JPanel currentJpnl, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        MataKuliah mtkl = (MataKuliah) matkulOption.getSelectedItem();
        if (mtkl != null) {
            frame.remove(currentJpnl);
            frame.add(new DetailRingkasanMataKuliahGUI(frame, mtkl, daftarMahasiswa, daftarMataKuliah));
        } else {
            JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
