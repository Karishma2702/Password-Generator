public class Password {
    private final String value;
    private final int length;

    public Password(String s) {
        this.value = s;
        this.length = s.length();
    }

    private int charType(char c) {
        if (Character.isUpperCase(c)) return 1;
        if (Character.isLowerCase(c)) return 2;
        if (Character.isDigit(c)) return 3;
        return 4;
    }

    public int getStrengthScore() {
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSymbol = false;
        for (char c : value.toCharArray()) {
            switch (charType(c)) {
                case 1 -> hasUpper = true;
                case 2 -> hasLower = true;
                case 3 -> hasDigit = true;
                case 4 -> hasSymbol = true;
            }
        }

        int score = 0;
        if (hasUpper) score++;
        if (hasLower) score++;
        if (hasDigit) score++;
        if (hasSymbol) score++;
        if (length >= 8) score++;
        if (length >= 16) score++;

        return score;
    }

    public String calculateScore() {
        int score = getStrengthScore();
        return switch (score) {
            case 6 -> "This is a very good password :D";
            case 4, 5 -> "This is a good password :)";
            case 3 -> "This is a medium password :/";
            default -> "This is a weak password :(";
        };
    }

    @Override
    public String toString() {
        return value;
    }
}

