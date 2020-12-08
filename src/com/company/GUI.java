package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton generateButton;
    private JLabel label;
    private JCheckBox containLowerCase, containUpperCase, containSymbols;
    private JSlider passwordLength;

    public GUI() {
        label = new JLabel("Preferences");
        generateButton = new JButton("Generate password");
        passwordLength = new JSlider(1,25);
        setUpButton();
        panel = new JPanel();
        setUpPanel();
        frame = new JFrame();
        setUpFrame();
        containLowerCase = new JCheckBox("Lower case");
        containUpperCase = new JCheckBox("Upper case");
        containSymbols = new JCheckBox("Contain symbols");
        addCheckBoxes();
        setUpSlider();
    }

    private void setUpButton() {
        generateButton.addActionListener(this);
    }

    private void setUpPanel() {
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(generateButton);
        panel.add(label);
    }

    private void setUpFrame() {
        frame.setPreferredSize(new Dimension(400, 300));
        frame.add(panel,BorderLayout.CENTER );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Password Generator");
        frame.pack();
        frame.setVisible(true);

    }

    private void addCheckBoxes() {
        panel.add(containLowerCase);
        panel.setVisible(true);
        panel.add(containUpperCase);
        panel.setVisible(true);
        panel.add(containSymbols);
        panel.setVisible(true);
    }

    private void setUpSlider() {
        Container speedPanel = new JPanel();
        speedPanel.setLayout(new GridLayout(2, 1));
        speedPanel.add(new JLabel("Password length", SwingConstants.CENTER));
        speedPanel.add(passwordLength);
        panel.add(speedPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
