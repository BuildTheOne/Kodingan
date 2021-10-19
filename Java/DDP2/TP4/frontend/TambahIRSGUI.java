package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import DDP2.TP4.backend.*;

public class TambahIRSGUI extends JPanel {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel();
    JLabel titleLabel = new JLabel("Tambah IRS");
    JLabel labelNPM = new JLabel("Pilih NPM");
    JComboBox<Mahasiswa> npmOption;
    JLabel labelMatkul = new JLabel("Pilih Matkul");
    JComboBox<MataKuliah> matkulOption;
    JButton addIRSBtn = new JButton("Tambahkan");
    JButton btnBack = new JButton("Kembali");

    public TambahIRSGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        frame.setLayout(new CardLayout());

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        Collections.sort(daftarMahasiswa);
        Collections.sort(daftarMataKuliah);
        Mahasiswa[] mhsList = daftarMahasiswa.toArray(new Mahasiswa[0]);
        MataKuliah[] matkulList = daftarMataKuliah.toArray(new MataKuliah[0]);
        npmOption = new JComboBox<Mahasiswa>(mhsList);
        matkulOption = new JComboBox<MataKuliah>(matkulList);

        addIRSBtn.addActionListener(e -> addIRS(frame));
        btnBack.addActionListener(e -> SistemAkademikGUI.addBtnListener(frame, mainPanel, new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah)));

        GroupLayout layout = new GroupLayout(panel1);
        panel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(titleLabel)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labelNPM)
            .addComponent(labelMatkul)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(npmOption)
            .addComponent(matkulOption)
            ))
        .addGroup(layout.createSequentialGroup()
            .addComponent(addIRSBtn)
            .addComponent(btnBack))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(titleLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelNPM)
            .addComponent(npmOption))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelMatkul)
            .addComponent(matkulOption))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(addIRSBtn)
            .addComponent(btnBack))
        );

        mainPanel.add(panel1, new GridBagConstraints());
        frame.add(mainPanel);
    }

    public void addIRS(JFrame frame) {
        Mahasiswa mhs = (Mahasiswa) npmOption.getSelectedItem();
        MataKuliah mtkl = (MataKuliah) matkulOption.getSelectedItem();
        if (mhs != null && mtkl != null) {
            JOptionPane.showMessageDialog(frame, mhs.addMatkul(mtkl));
        } else {
            JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
