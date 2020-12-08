package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

public class GUI {
    private final JFrame frame;
    private final JTabbedPane tabbedPane;
    private final JPanel passwordGeneratorTab, generatedPasswordsTab;
    private final JButton generateButton, copyToClipBoardButton;
    private final JLabel preferencesLabel;
    private final JCheckBox useLower, useUpper, useSymbols, useNumeric;
    private final JSlider passwordLength;
    private final PasswordGenerator passwordGenerator;
    private final JTextArea passwordField;

    public GUI() {
        passwordField = new JTextArea();
        passwordGenerator = new PasswordGenerator();
        preferencesLabel = new JLabel("Preferences");
        generateButton = new JButton("Generate password");
        copyToClipBoardButton = new JButton("Copy to clipboard");
        setUpButtons();
        passwordGeneratorTab = new JPanel();
        setUpPasswordGeneratorTab();
        generatedPasswordsTab = new JPanel();
        setUpGeneratedPasswordsTab();
        tabbedPane = new JTabbedPane();
        setUpTabbedPane();
        passwordLength = new JSlider(1, 25);
        setUpSlider();
        frame = new JFrame();
        setUpFrame();
        useLower = new JCheckBox("Lower case");
        useUpper = new JCheckBox("Upper case");
        useSymbols = new JCheckBox("Symbols");
        useNumeric = new JCheckBox("Numeric");
        addCheckBoxes();
    }

    private void setUpButtons() {
        generateButton.addActionListener(this::generateButtonPressed);
        copyToClipBoardButton.addActionListener(this::copyToClipBoardButtonPressed);
    }

    private void setUpTabbedPane() {
        tabbedPane.addTab("Generate a password", passwordGeneratorTab);
        tabbedPane.addTab("Generated passwords", generatedPasswordsTab);
    }

    private void setUpPasswordGeneratorTab() {
        passwordGeneratorTab.add(passwordField);
        passwordGeneratorTab.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        passwordGeneratorTab.setLayout(new GridLayout(0, 1));
        passwordGeneratorTab.add(generateButton);
        passwordGeneratorTab.add(copyToClipBoardButton);
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

    public void generateButtonPressed(ActionEvent e) {
        if (isValid()) {
            String password = passwordGenerator.generatePassword(passwordLength.getValue(),
                    useLower.isSelected(), useUpper.isSelected(),
                    useSymbols.isSelected(), useNumeric.isSelected());
            passwordField.setText(password);
        } else {
            JOptionPane.showMessageDialog(frame, "No options have been selected.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void copyToClipBoardButtonPressed(ActionEvent e) {
        StringSelection stringSelection = new StringSelection(passwordField.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public boolean isValid() {
        int selectedCount = 0;
        if (useLower.isSelected()) {
            selectedCount++;
        }
        if (useUpper.isSelected()) {
            selectedCount++;
        }
        if (useSymbols.isSelected()) {
            selectedCount++;
        }
        if (useNumeric.isSelected()) {
            selectedCount++;
        }
        return selectedCount != 0;
    }
}
