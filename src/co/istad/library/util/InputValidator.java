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

    public String readOptionalText(String oldValue, String message) {
        while (true) {
            System.out.print(message + "(" + oldValue + "): ");
            String txt = input.nextLine().trim();
            if (txt.isEmpty()) {
                return oldValue;
            }
            if (!txt.matches("[A-Za-z ]+")) {
                System.out.println(Color.RED + "❌ Only letters allowed!" + Color.RESET);
                continue;
            }
            return txt;
        }
    }

    public int readOptionalInt(int oldValue, String message) {
        while (true) {
            System.out.print(message + "(" + oldValue + "): ");
            String txt = input.nextLine().trim();
            if (txt.isEmpty()) {
                return oldValue;
            }
            try {
                return Integer.parseInt(txt);
            } catch (NumberFormatException e) {
                System.out.println(Color.RED + "❌ Please enter a valid number" + Color.RESET);
            }
        }
    }

    public String readOptionalIsbn(String oldValue, String message) {
        while (true) {
            System.out.print(message + "(" + oldValue + "): ");
            String txt = input.nextLine().trim();
            if (txt.isEmpty()) {
                return oldValue;
            }
            if (!txt.matches("\\d{5,20}+")) {
                System.out.println(Color.RED + "❌ ISBN must be 5-20 digits!" + Color.RESET);
                continue;
            }
            return txt;
        }
    }

    public String readAddress(String message) {
        while (true) {
            System.out.print(message);
            // I use trim()
            // Because i want to remove any leading or trailing whitespace characters from the input string.
            String addr = input.nextLine().trim();
            if (addr.isEmpty()) {
                System.out.println(Color.RED + "❌ Address cannot be empty!" + Color.RESET);
                continue;
            }
            if (!addr.matches("[A-Za-z0-9 ,./#-]+")) {
                System.out.println(Color.RED + "❌ Address can only contain letters, numbers, and , . / # - characters!" + Color.RESET);
                continue;
            }
            return addr;
        }
    }

    public String readPhone(String message) {
        while (true) {
            System.out.print(message);
            // I use trim()
            // Because i want to remove any leading or trailing whitespace characters from the input string.
            String phone = input.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println(Color.RED + "❌ Phone number cannot be empty!" + Color.RESET);
                continue;
            }
            if (!phone.matches("[0-9+\\- ]+")) {
                System.out.println(Color.RED + "❌ Phone number can only contain digits, '+', '-' and spaces!" + Color.RESET);
                continue;
            }
            return phone;
        }
    }

    public String readEmail(String message) {
        while (true) {
            System.out.print(message);
            // I use trim()
            // Because i want to remove any leading or trailing whitespace characters from the input string.
            String email = input.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println(Color.RED + "❌ Email cannot be empty!" + Color.RESET);
                continue;
            }
            if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
                System.out.println(Color.RED + "❌ Invalid email format!" + Color.RESET);
                continue;
            }
            return email;
        }
    }

    public String readDate(String message) {
        while (true) {
            System.out.print(message);
            // I use trim()
            // Because i want to remove any leading or trailing whitespace characters from the input string.
            String date = input.nextLine().trim();
            if (date.isEmpty()) {
                System.out.println(Color.RED + "❌ Date cannot be empty!" + Color.RESET);
                continue;
            }
            if (!date.matches("\\\\d{4}-\\\\d{2}-\\\\d{2}")) {
                System.out.println(Color.RED + "❌ Date must be in format YYYY-MM-DD!" + Color.RESET);
                continue;
            }
            return date;
        }
    }
}
