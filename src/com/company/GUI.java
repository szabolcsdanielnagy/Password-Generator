package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private final JFrame frame;
    private final JTabbedPane tabbedPane;
    private final JPanel passwordGeneratorTab, generatedPasswordsTab;
    private final JButton generateButton;
    private final JLabel preferencesLabel;
    private final JCheckBox useLower, useUpper, useSymbols, useNumeric;
    private final JSlider passwordLength;
    private final PasswordGenerator passwordGenerator;

    public GUI() {
        tabbedPane = new JTabbedPane();
        preferencesLabel = new JLabel("Preferences");
        generateButton = new JButton("Generate password");
        passwordLength = new JSlider(1, 25);
        setUpButton();
        passwordGeneratorTab = new JPanel();
        generatedPasswordsTab = new JPanel();
        setUpPasswordGeneratorTab();
        setUpGeneratedPasswordsTab();
        frame = new JFrame();
        setUpFrame();
        useLower = new JCheckBox("Lower case");
        useUpper = new JCheckBox("Upper case");
        useSymbols = new JCheckBox("Contain symbols");
        useNumeric = new JCheckBox("Contain numeric");
        addCheckBoxes();
        setUpSlider();
        passwordGenerator = new PasswordGenerator();
        setUpTabbedPane();
    }

    private void setUpButton() {
        generateButton.addActionListener(this);
    }

    private void setUpTabbedPane() {
        tabbedPane.addTab("Password generator", passwordGeneratorTab);
        tabbedPane.addTab("Generated passwords", generatedPasswordsTab);
    }

    private void setUpPasswordGeneratorTab() {
        passwordGeneratorTab.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        passwordGeneratorTab.setLayout(new GridLayout(0, 1));
        passwordGeneratorTab.add(generateButton);
        passwordGeneratorTab.add(preferencesLabel);
    }

    private void setUpGeneratedPasswordsTab() {
        generatedPasswordsTab.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        generatedPasswordsTab.setLayout(new GridLayout(0, 1));
    }

    private void setUpFrame() {
        frame.setPreferredSize(new Dimension(400, 300));
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Password Generator");
        frame.pack();
        frame.setVisible(true);

    }

    private void addCheckBoxes() {
        passwordGeneratorTab.add(useLower);
        passwordGeneratorTab.setVisible(true);
        passwordGeneratorTab.add(useUpper);
        passwordGeneratorTab.setVisible(true);
        passwordGeneratorTab.add(useSymbols);
        passwordGeneratorTab.setVisible(true);
        passwordGeneratorTab.add(useNumeric);
        passwordGeneratorTab.setVisible(true);
    }

    private void setUpSlider() {
        Container speedPanel = new JPanel();
        speedPanel.setLayout(new GridLayout(2, 1));
        speedPanel.add(new JLabel("Password length", SwingConstants.CENTER));
        speedPanel.add(passwordLength);
        passwordGeneratorTab.add(speedPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String password = passwordGenerator.generatePassword(passwordLength.getValue(), useLower.isSelected(), useUpper.isSelected(), useSymbols.isSelected(), useNumeric.isSelected());
        JOptionPane.showMessageDialog(frame,password,"Generated password", JOptionPane.INFORMATION_MESSAGE);
    }
}
