package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import DDP2.TP4.backend.*;

public class RingkasanMahasiswaGUI extends JPanel {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel();
    JLabel titleLabel = new JLabel("Ringkasan Mahasiswa");
    JLabel labelNama = new JLabel("NPM Mahasiswa");
    JComboBox<Mahasiswa> npmOption;
    JButton findMhsBtn = new JButton("Cari");
    JButton btnBack = new JButton("Kembali");

    public RingkasanMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        frame.setLayout(new CardLayout());

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        Collections.sort(daftarMahasiswa);
        Mahasiswa[] mhsList = daftarMahasiswa.toArray(new Mahasiswa[0]);
        npmOption = new JComboBox<Mahasiswa>(mhsList);

        findMhsBtn.addActionListener(e -> findMhs(frame, mainPanel, daftarMahasiswa, daftarMataKuliah));
        btnBack.addActionListener(e -> SistemAkademikGUI.addBtnListener(frame, mainPanel, new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah)));

        GroupLayout layout = new GroupLayout(panel1);
        panel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(titleLabel)
        .addGroup(layout.createSequentialGroup()
            .addComponent(labelNama)
            .addComponent(npmOption))
        .addGroup(layout.createSequentialGroup()
            .addComponent(findMhsBtn)
            .addComponent(btnBack))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(titleLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelNama)
            .addComponent(npmOption))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(findMhsBtn)
            .addComponent(btnBack))
        );

        mainPanel.add(panel1, new GridBagConstraints());
        frame.add(mainPanel);
    }

    public void findMhs(JFrame frame, JPanel currentJpnl, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        Mahasiswa mhs = (Mahasiswa) npmOption.getSelectedItem();
        if (mhs != null) {
            frame.remove(currentJpnl);
            frame.add(new DetailRingkasanMahasiswaGUI(frame, mhs, daftarMahasiswa, daftarMataKuliah));
        } else {
            JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
