package co.istad.library.util;

import java.util.Scanner;

public record InputValidator(Scanner input) {

    public String readText(String message) {
        while (true) {
            System.out.print(message);
            // I use trim() because i want to remove any leading or trailing whitespace characters from the input string.
            String text = input.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println(Color.RED + "❌ Input cannot be empty!" + Color.RESET);
                continue;
            }
            if (!text.matches("[A-Za-z ]+")) {
                System.out.println(Color.RED + "❌ Only letters allowed!" + Color.RESET);
                continue;
            }
            return text;
        }
    }

    public String readIsbn(String message) {
        while (true) {
            System.out.print(message);
            String isbn = input.nextLine().trim();
            if (isbn.isEmpty()) {
                System.out.println(Color.RED + "❌ ISBN cannot be empty!" + Color.RESET);
                continue;
            }
            if (!isbn.matches("[0-9{5,20}]+")) {
                System.out.println(Color.RED + "❌ ISBN must be numbers only!" + Color.RESET);
                continue;
            }
            return isbn;
        }
    }

    public int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Color.RED + "❌ Please enter a valid number" + Color.RESET);
            }
        }
    }
}
