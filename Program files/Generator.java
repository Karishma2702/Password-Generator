import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean upper, boolean lower, boolean number, boolean symbol) {
        this.alphabet = new Alphabet(upper, lower, number, symbol);
    }

    public void mainLoop() {
        System.out.println("Welcome to Ziz Password Services :)");
        printMenu();

        String option;
        do {
            option = keyboard.next();
            switch (option) {
                case "1" -> {
                    generatePasswordFlow();
                    printMenu();
                }
                case "2" -> {
                    checkPasswordStrength();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> System.out.println("Closing the program. Bye!");
                default -> {
                    System.out.println("Invalid choice.");
                    printMenu();
                }
            }
        } while (!option.equals("4"));
    }

    private void generatePasswordFlow() {
        System.out.println("Answer Yes/No to the following:");

        boolean upper = getYesNo("Include uppercase letters?");
        boolean lower = getYesNo("Include lowercase letters?");
        boolean number = getYesNo("Include numbers?");
        boolean symbol = getYesNo("Include symbols?");

        if (!(upper || lower || number || symbol)) {
            System.out.println("At least one character type must be selected.");
            return;
        }

        System.out.print("Enter password length: ");
        int length = keyboard.nextInt();

        Generator generator = new Generator(upper, lower, number, symbol);
        Password password = generator.generatePassword(length);
        System.out.println("Generated password: " + password);
    }

    private Password generatePassword(int length) {
        String chars = alphabet.getPool();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(chars.length());
            result.append(chars.charAt(index));
        }

        return new Password(result.toString());
    }

    private void checkPasswordStrength() {
        System.out.print("Enter your password: ");
        String input = keyboard.next();
        Password p = new Password(input);
        System.out.println(p.calculateScore());
    }

    private boolean getYesNo(String prompt) {
        String input;
        do {
            System.out.println(prompt + " (Yes/No)");
            input = keyboard.next().trim();
        } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
        return input.equalsIgnoreCase("yes");
    }

    private void printMenu() {
        System.out.println("\n1 - Generate Password");
        System.out.println("2 - Check Password Strength");
        System.out.println("3 - Useful Info");
        System.out.println("4 - Exit");
        System.out.print("Choice: ");
    }

    private void printUsefulInfo() {
        System.out.println("""
                Tips for strong passwords:
                - Minimum 8 characters (preferably 16+)
                - Include uppercase, lowercase, numbers, symbols
                - Avoid reuse across sites
                - Don’t use personal info or common words
                """);
    }
}

