package com.company;

import java.security.SecureRandom;
import java.util.ArrayList;

public class PasswordGenerator {

    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String NUMERIC = "0123456789";
    private final String SYMBOLS = "!@#$%^&*_=+-/";
    private SecureRandom random;
    private boolean useLower;
    private boolean useUpper;
    private boolean useNumeric;
    private boolean useSymbols;
    private ArrayList<String> usages;

    public PasswordGenerator() {
        random = new SecureRandom();
        usages = new ArrayList<>();
        useLower = false;
        useUpper = false;
        useNumeric = false;
        useSymbols = false;
    }

    public String generatePassword(int length, boolean useLower, boolean useUpper,
                                   boolean useSymbols, boolean useNumeric) {
        this.useLower = useLower;
        this.useUpper = useUpper;
        this.useSymbols = useSymbols;
        this.useNumeric = useNumeric;
        setUpUsages();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String charCategory = usages.get(random.nextInt(usages.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        usages = new ArrayList<>();
        return password.toString();
    }

    public void setUpUsages() {
        if (useLower) {
            usages.add(LOWER);
        }
        if (useNumeric) {
            usages.add(NUMERIC);
        }
        if (useSymbols) {
            usages.add(SYMBOLS);
        }
        if (useUpper) {
            usages.add(UPPER);
        }
    }
}
