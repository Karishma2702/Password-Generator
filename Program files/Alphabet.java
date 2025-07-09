public class Alphabet {

    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "0123456789";
    public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";

    private final String pool;

    public Alphabet(boolean upper, boolean lower, boolean number, boolean symbols) {
        StringBuilder sb = new StringBuilder();
        if (upper) sb.append(UPPERCASE);
        if (lower) sb.append(LOWERCASE);
        if (number) sb.append(NUMBERS);
        if (symbols) sb.append(SYMBOLS);
        pool = sb.toString();
    }

    public String getPool() {
        return pool;
    }
}
