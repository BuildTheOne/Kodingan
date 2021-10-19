package DDP2.Tugas4;
// Nama : Ignatius Henriyanto Primai Renda
// NPM : 2006525002
// Kelas : DDP-2 E

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorSimple extends JFrame {
  private JTextField textField = new JTextField("");

  public CalculatorSimple() {
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(textField, BorderLayout.NORTH);
    textField.setEditable(false);

    JPanel buttonPanel = new JPanel(new GridLayout(4, 3));

    // Add buttons to the panel
    for (int i = 1; i <= 9; i++) {
      String j = Integer.toString(i);
      JButton btn = new JButton("" + i);
      btn.addActionListener(new NumberAdd(j));
      buttonPanel.add(btn);
    }

    JButton btn0 = new JButton("" + 0);
    JButton btnDot = new JButton(".");
    JButton btnErase = new JButton("CE");
    btn0.addActionListener(new NumberAdd("0"));
    btnDot.addActionListener(new NumberAdd("."));
    btnErase.addActionListener(new ClearText());
    buttonPanel.add(btn0);
    buttonPanel.add(btnDot);
    buttonPanel.add(btnErase);

    mainPanel.add(buttonPanel, BorderLayout.CENTER);
    add(mainPanel);
  }

  class NumberAdd implements ActionListener {
    public String num;

    public NumberAdd(String num) {
      this.num = num;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String txt = textField.getText();
      textField.setText(txt + num);
    }
  }

  class ClearText implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      textField.setText("");
    }
  }

  public static void main(String[] args) {
    CalculatorSimple frame = new CalculatorSimple();
    frame.setTitle("Calculator Simple");
    frame.setSize(400, 250);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
