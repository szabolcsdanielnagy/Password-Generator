package com.company;

import java.security.SecureRandom;
import java.util.ArrayList;

public class PasswordGenerator {

    private final SecureRandom random;
    private boolean useLower;
    private boolean useUpper;
    private boolean useNumeric;
    private boolean useSymbols;
    private ArrayList<String> selectedCharacterPackages;

    public PasswordGenerator() {
        random = new SecureRandom();
        selectedCharacterPackages = new ArrayList<>();
        useLower = false;
        useUpper = false;
        useNumeric = false;
        useSymbols = false;
    }

    public String generatePassword(int length, boolean useLower,
                                   boolean useUpper, boolean useSymbols,
                                   boolean useNumeric) {
        this.useLower = useLower;
        this.useUpper = useUpper;
        this.useSymbols = useSymbols;
        this.useNumeric = useNumeric;
        setUpSelectedCharacterPackages();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String charCategory = selectedCharacterPackages.get(random.nextInt(selectedCharacterPackages.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        selectedCharacterPackages = new ArrayList<>();
        return password.toString();
    }

    public void setUpSelectedCharacterPackages() {
        if (useLower) {
            String LOWER = "abcdefghijklmnopqrstuvwxyz";
            selectedCharacterPackages.add(LOWER);
        }
        if (useNumeric) {
            String NUMERIC = "0123456789";
            selectedCharacterPackages.add(NUMERIC);
        }
        if (useSymbols) {
            String SYMBOLS = "!@#$%^&*_=+-/";
            selectedCharacterPackages.add(SYMBOLS);
        }
        if (useUpper) {
            String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            selectedCharacterPackages.add(UPPER);
        }
    }
}
