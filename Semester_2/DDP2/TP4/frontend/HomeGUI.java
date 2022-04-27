package DDP2.TP4.frontend;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
import java.util.ArrayList;

import DDP2.TP4.backend.*;

public class HomeGUI extends JPanel {
        // Add the components and panels needed
        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel panel1 = new JPanel();
        JLabel titleLabel = new JLabel("Selamat datang di Sistem Akademik");
        JButton addMhs = new JButton("Tambah Mahasiswa");
        JButton addMatkul = new JButton("Tambah Mata Kuliah");
        JButton addIRS = new JButton("Tambah IRS");
        JButton deleteIRS = new JButton("Hapus IRS");
        JButton detailMhs = new JButton("Lihat Ringkasan Mahasiswa");
        JButton detailMatkul = new JButton("Lihat Ringkasan Mata Kuliah");

        public HomeGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
                // Set frame layout as CardLayout
                frame.setLayout(new CardLayout());

                // Center the title
                titleLabel.setHorizontalAlignment(JLabel.CENTER);
                titleLabel.setFont(SistemAkademikGUI.fontTitle);

                // Use lambda expression to addListener to buttons
                addMhs.addActionListener(e -> addBtnListener(frame, mainPanel, new TambahMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah)));
                addMatkul.addActionListener(e -> addBtnListener(frame, mainPanel, new TambahMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah)));
                addIRS.addActionListener(e -> addBtnListener(frame, mainPanel, new TambahIRSGUI(frame, daftarMahasiswa, daftarMataKuliah)));
                deleteIRS.addActionListener(e -> addBtnListener(frame, mainPanel, new HapusIRSGUI(frame, daftarMahasiswa, daftarMataKuliah)));
                detailMatkul.addActionListener(e -> addBtnListener(frame, mainPanel, new RingkasanMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah)));
                detailMhs.addActionListener(e -> addBtnListener(frame, mainPanel, new RingkasanMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah)));

                // Set mainPanel layout as GroupLayout so the components can be adjusted
                GroupLayout layout = new GroupLayout(panel1);
                panel1.setLayout(layout);
                // Set gaps
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Add the components to the layout
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titleLabel)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(addMhs)
                                .addComponent(addIRS)
                                .addComponent(detailMhs))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(addMatkul)
                                .addComponent(deleteIRS)
                                .addComponent(detailMatkul)))
                );
                layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(addMhs)
                        .addComponent(addMatkul))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(addIRS)
                        .addComponent(deleteIRS))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(detailMhs)
                        .addComponent(detailMatkul))
                );

                // Add the panel to the frame
                mainPanel.add(panel1, new GridBagConstraints());
                frame.add(mainPanel);
        }

        // Method to remove current panel and add (& show) new panel
        public static void addBtnListener(JFrame frame, JPanel currentJpnl, JPanel newJpnl) {
                frame.remove(currentJpnl);
                frame.add(newJpnl);
        }
}
