package co.istad.library.util;

import co.istad.library.model.Member;
import co.istad.library.service.BookService;
import co.istad.library.service.MemberService;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class ViewUtil {
    public static void printBanner() {
        System.out.println(Color.BOLD_CYAN + """
                 ___ ____ _____  _    ____    _     ___ ____  ____      _    ______   __
                |_ _/ ___|_   _|/ \\  |  _ \\  | |   |_ _| __ )|  _ \\    / \\  |  _ \\ \\ / /
                 | |\\___ \\ | | / _ \\ | | | | | |    | ||  _ \\| |_) |  / _ \\ | |_) \\ V /\s
                 | | ___) || |/ ___ \\| |_| | | |___ | || |_) |  _ <  / ___ \\|  _ < | | \s
                |___|____/ |_/_/   \\_\\____/  |_____|___|____/|_| \\_\\/_/   \\_\\_| \\_\\|_| \s""" + Color.RESET);
    }

    public static void showMainMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        String title = Color.BOLD_CYAN + "LIBRARY MANAGEMENT SYSTEM" + Color.RESET;
        table.addCell(title, cellStyle);
        table.addCell(Color.YELLOW + "1. Book Management" + Color.RESET);
        table.addCell(Color.YELLOW + "2. Member Management" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Borrow & Return System" + Color.RESET);
        table.addCell(Color.YELLOW + "4. Activity Logs" + Color.RESET);
        table.addCell(Color.YELLOW + "5. Reports & Dashboards" + Color.RESET);
        table.addCell(Color.BOLD_RED + "6. Exit the Program" + Color.RESET);
        System.out.println(table.render());
    }

    public static void showBookMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        String title = Color.BOLD_CYAN + "BOOK MANAGEMENT" + Color.RESET;
        table.addCell(title, cellStyle);
        table.addCell(Color.YELLOW + "1. Add New Books" + Color.RESET);
        table.addCell(Color.YELLOW + "2. Update Book Information" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Delete Book" + Color.RESET);
        table.addCell(Color.YELLOW + "4. Search Books" + Color.RESET);
        table.addCell(Color.YELLOW + "5. Display/List all Books" + Color.RESET);
        table.addCell(Color.YELLOW + "6. Sort Books" + Color.RESET);
        table.addCell(Color.BOLD_RED + "7. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void showSearchMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "SEARCH BOOKS" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + "1. Search by Title" + Color.RESET);
        table.addCell(Color.YELLOW + "2. Search by Author" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Search by Category" + Color.RESET);
        table.addCell(Color.YELLOW + "4. Search by ISBN" + Color.RESET);
        table.addCell(Color.BOLD_RED + "5. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void bookMenuLoop(Scanner input, BookService bookService) {
        BookView bookView = new BookView(bookService, new InputValidator(input));
        while (true) {
            showBookMenu();
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-6): " + Color.RESET);
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    System.out.println(Color.BOLD_GREEN + "➕ Add New Books" + Color.RESET);
                    bookView.addNewBook();
                }
                case "2" -> {
                    System.out.println(Color.BOLD_BLUE + "✏️ Update Book Information" + Color.RESET);
                    bookView.updateBookInfo();
                }
                case "3" -> {
                    System.out.println(Color.BOLD_RED + "🗑️ Delete Book" + Color.RESET);
                    bookView.deleteBook();
                }
                case "4" -> {
                    System.out.println(Color.BOLD_PURPLE + "🔍 Search Books" + Color.RESET);
                    ViewUtil.showSearchMenu();
                    bookView.searchBooks();
                    continue;
                }
                case "5" -> {
                    System.out.println(Color.BOLD_YELLOW + "📄 Display/List All Books" + Color.RESET);
                    bookView.navigatePagination();
                    continue;
                }
                case "6" -> {
                    System.out.println(Color.BOLD_YELLOW + "🔽 Sort Books" + Color.RESET);
                    bookView.displaySortedBooks();
                }
                case "7" -> {
                    System.out.println(Color.BOLD_CYAN + "🔙 Returning to Main Menu..." + Color.RESET);
                    return;
                }
                default -> System.out.println(Color.BOLD_RED + "⚠️ Invalid choice. Try again." + Color.RESET);
            }
            System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
            input.nextLine();
        }
    }
    // Borrow Part:
    public static void borrowAndReturnMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "BORROW BOOKS" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + "1. Borrow Books" + Color.RESET);
        table.addCell(Color.YELLOW + "2. Return Books" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Display/List All Books" + Color.RESET);
        table.addCell(Color.RED + "4. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void borrowMenu(Scanner input){
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "BORROW BOOKS" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + "1. Enter Book Name: " + Color.RESET);
        table.addCell(Color.YELLOW + "2. Return Books" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Display/List All Books" + Color.RESET);
        table.addCell(Color.RED + "4. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void borrowBookLoop(Scanner input, BookService bookService) {
        borrowAndReturnMenu();
        BookView bookView = new BookView(bookService, new InputValidator(input));
        System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-4): " + Color.RESET);
        String choice = input.nextLine().trim();
        switch (choice) {
            case "1" -> System.out.println(Color.YELLOW + "You have select borrow books option" + Color.RESET);
            case "2" -> System.out.println(Color.YELLOW + "You have select return books option" + Color.RESET);
            case "3" -> {
                System.out.println(Color.YELLOW + "Display/List All Books" + Color.RESET);
                bookView.navigatePagination();
                bookView.displayBooksPage();
            }
            case "4" -> {
                System.out.println(Color.RED + "You have select back to main menu option" + Color.RESET);
            }
            default -> {
                System.out.println(Color.BOLD_CYAN + "Invalid choice. Please try again!" + Color.RESET);
                borrowAndReturnMenu();
            }
        }
    }
    public static void showMemberManagementMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        String title = Color.BOLD_CYAN + "MEMBER MANAGEMENT" + Color.RESET;
        table.addCell(title, cellStyle);
        table.addCell(Color.YELLOW + "1. Add New Member" + Color.RESET);
        table.addCell(Color.YELLOW + "2. Update Member Information" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Delete Member" + Color.RESET);
        table.addCell(Color.YELLOW + "4. Search Member (Name/Id)" + Color.RESET);
        table.addCell(Color.YELLOW + "5. Display/List all Member" + Color.RESET);
        table.addCell(Color.YELLOW + "6. Sort Member" + Color.RESET);
        table.addCell(Color.BOLD_RED + "7. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void memberMenuLoop(Scanner input, MemberService memberService) {
        MemberView memberView = new MemberView(memberService, new InputValidator(input));
        while (true) {
            showMemberManagementMenu();
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-6): " + Color.RESET);
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    System.out.println(Color.BOLD_GREEN + "➕ Add New Members" + Color.RESET);
                    memberView.addNewMember();
                }
                case "2" -> {
                    System.out.println(Color.BOLD_BLUE + "✏️ Update Member Information" + Color.RESET);
                }
                case "3" -> {
                    System.out.println(Color.BOLD_RED + "🗑️ Delete Member" + Color.RESET);
                }
                case "4" -> {
                    System.out.println(Color.BOLD_PURPLE + "🔍 Search Member" + Color.RESET);
                }
                case "5" -> {
                    System.out.println(Color.BOLD_YELLOW + "📄 Display/List All Members" + Color.RESET);
                    memberView.navigatePagination();
                    continue;
                }
                case "6" -> {
                    System.out.println(Color.BOLD_YELLOW + "🔽 Sort Members" + Color.RESET);
                }
                case "7" -> {
                    System.out.println(Color.BOLD_CYAN + "🔙 Returning to Main Menu..." + Color.RESET);
                    return;
                }
                default -> System.out.println(Color.BOLD_RED + "⚠️ Invalid choice. Try again." + Color.RESET);
            }
            System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
            input.nextLine();
        }
    }
}
