package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;

import DDP2.TP4.backend.*;

public class TambahMahasiswaGUI extends JPanel {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel();
    JLabel titleLabel = new JLabel("Tambah Mahasiswa");
    JLabel labelNama = new JLabel("Nama");
    JTextField fieldNama = new JTextField();
    JLabel labelNPM = new JLabel("NPM");
    JTextField fieldNpm = new JTextField();
    JButton addMhsBtn = new JButton("Tambahkan");
    JButton btnBack = new JButton("Kembali");

    public TambahMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        frame.setLayout(new CardLayout());

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        addMhsBtn.addActionListener(e -> addMahasiswa(frame, daftarMahasiswa));
        btnBack.addActionListener(e -> addBtnListener(frame, mainPanel, new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah)));

        GroupLayout layout = new GroupLayout(panel1);
        panel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(titleLabel)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labelNama)
            .addComponent(labelNPM)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(fieldNama)
            .addComponent(fieldNpm)
            ))
        .addGroup(layout.createSequentialGroup()
            .addComponent(addMhsBtn)
            .addComponent(btnBack))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(titleLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelNama)
            .addComponent(fieldNama))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelNPM)
            .addComponent(fieldNpm))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(addMhsBtn)
            .addComponent(btnBack))
        );

        mainPanel.add(panel1, new GridBagConstraints());
        frame.add(mainPanel);
    }

    public void addMahasiswa(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa) {
        String nama = fieldNama.getText();
        String npmStr = fieldNpm.getText();

        if (!nama.equals("") && !npmStr.equals("")) {
            long npm = Long.parseLong(npmStr);
            if (daftarMahasiswa.contains(SistemAkademikGUI.getMahasiswa(npm))) {
                JOptionPane.showMessageDialog(frame, "NPM " + npmStr + " sudah pernah ditambahkan sebelumnya", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                daftarMahasiswa.add(new Mahasiswa(nama, npm));
                JOptionPane.showMessageDialog(frame, "Mahasiswa " + npmStr + " - " + nama + " berhasil ditambahkan");
                fieldNama.setText("");
                fieldNpm.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void addBtnListener(JFrame frame, JPanel currentJpnl, JPanel newJpnl) {
        frame.remove(currentJpnl);
        frame.add(newJpnl);
    }
}
