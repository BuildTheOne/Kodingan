package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;

import DDP2.TP4.backend.*;

public class TambahMataKuliahGUI extends JPanel {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel panel1 = new JPanel();
    JLabel titleLabel = new JLabel("Tambah Mata Kuliah");
    JLabel labelKode = new JLabel("Kode Mata Kuliah");
    JTextField fieldKode = new JTextField();
    JLabel labelNama = new JLabel("Nama Mata Kuliah");
    JTextField fieldNama = new JTextField();
    JLabel labelSks = new JLabel("SKS");
    JTextField fieldSks = new JTextField();
    JLabel labelKapasitas = new JLabel("Kapasitas");
    JTextField fieldKapasitas = new JTextField();
    JButton addMatkulBtn = new JButton("Tambahkan");
    JButton btnBack = new JButton("Kembali");

    public TambahMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        frame.setLayout(new CardLayout());

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        // panel1.add(labelKode);
        // panel1.add(fieldKode);
        // panel1.add(labelNama);
        // panel1.add(fieldNama);
        // panel1.add(labelSks);
        // panel1.add(fieldSks);
        // panel1.add(labelKapasitas);
        // panel1.add(fieldKapasitas);
        // panel1.add(addMatkulBtn);
        // panel1.add(btnBack);

        addMatkulBtn.addActionListener(e -> addMatkul(frame, daftarMataKuliah));
        btnBack.addActionListener(e -> addBtnListener(frame, mainPanel, new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah)));

        GroupLayout layout = new GroupLayout(panel1);
        panel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(titleLabel)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labelKode)
            .addComponent(labelNama)
            .addComponent(labelSks)
            .addComponent(labelKapasitas)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(fieldKode)
            .addComponent(fieldNama)
            .addComponent(fieldSks)
            .addComponent(fieldKapasitas)
            ))
        .addGroup(layout.createSequentialGroup()
            .addComponent(addMatkulBtn)
            .addComponent(btnBack))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(titleLabel)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelKode)
            .addComponent(fieldKode))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelNama)
            .addComponent(fieldNama))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelSks)
            .addComponent(fieldSks))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(labelKapasitas)
            .addComponent(fieldKapasitas))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(addMatkulBtn)
            .addComponent(btnBack))
        );

        mainPanel.add(panel1, new GridBagConstraints());
        frame.add(mainPanel);
    }

    public void addMatkul(JFrame frame, ArrayList<MataKuliah> daftarMataKuliah) {
        String kode = fieldKode.getText();
        String nama = fieldNama.getText();
        String sks = fieldSks.getText();
        String kapasitas = fieldKapasitas.getText();
        
        if (!kode.equals("") && !nama.equals("") && !sks.equals("") && !kapasitas.equals("")) {
            nama = fieldNama.getText().charAt(0) < 91 ? fieldNama.getText() : Character.toString(fieldNama.getText().charAt(0) - 32) + fieldNama.getText().substring(1);
            if (daftarMataKuliah.contains(SistemAkademikGUI.getMataKuliah(nama))) {
                JOptionPane.showMessageDialog(frame, "Mata Kuliah " + nama + " sudah pernah ditambahkan sebelumnya", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                daftarMataKuliah.add(new MataKuliah(kode, nama, Integer.parseInt(sks), Integer.parseInt(kapasitas)));
                JOptionPane.showMessageDialog(frame, "Mata Kuliah " + nama + " berhasil ditambahkan");
                fieldKode.setText("");
                fieldNama.setText("");
                fieldSks.setText("");
                fieldKapasitas.setText("");
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
