package co.istad.library.util;

import co.istad.library.model.Book;
import co.istad.library.model.Member;
import co.istad.library.service.BookService;
import co.istad.library.service.BorrowService;
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
        table.addCell(Color.YELLOW + "4. Reports & Dashboards" + Color.RESET);
        table.addCell(Color.BOLD_RED + "5. Exit the Program" + Color.RESET);
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

    public static void bookMenuLoop(Scanner input, BookService bookService, MemberService memberService, BorrowService borrowService) {
        BookView bookView = new BookView(bookService, new InputValidator(input), memberService, borrowService);
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
    public static void borrowMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "BORROW BOOKS" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + "1. Borrow Books" + Color.RESET);
        table.addCell(Color.YELLOW + "2. Return Books" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Display Available Books" + Color.RESET);
        table.addCell(Color.YELLOW + "4. Display All Borrow Records" + Color.RESET);
        table.addCell(Color.RED + "5. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void borrowBookLoop(Scanner input, BookService bookService, MemberService memberService, BorrowService borrowService) {
        BookView bookView = new BookView(bookService, new InputValidator(input), memberService, borrowService);
        Member memberData = bookView.memberVerify();
        if (memberData == null) return;
        while (true) {
            borrowMenu();
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-4): " + Color.RESET);
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    System.out.println(Color.YELLOW + "You have select borrow books option" + Color.RESET);
                    bookView.borrowBook(memberData);
                }
                case "2" -> {
                    System.out.println(Color.YELLOW + "You have select return books option" + Color.RESET);
                    bookView.returnBook(memberData);
                }
                case "3" -> {
                    System.out.println(Color.YELLOW + "Display Available Books" + Color.RESET);
                    bookView.navigatePagination();
                }
                case "4" -> {
                    System.out.println(Color.YELLOW + "Display All Borrow Records" + Color.RESET);
                    bookView.navigateBorrowPagination();
                }
                case "5" -> {
                    System.out.println(Color.RED + "You have select back to main menu option" + Color.RESET);
                    return;
                }
                default -> System.out.println(Color.BOLD_CYAN + "Invalid choice. Please try again!" + Color.RESET);
            }
        }
    }

    // Report & Dashboard
    public static void reportMenu() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Reports & Dashboards" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + "1. Total Books" + Color.RESET);
        table.addCell(Color.YELLOW + "2. Total Members" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Total Borrow Books" + Color.RESET);
        table.addCell(Color.YELLOW + "4. Available Books" + Color.RESET);
        table.addCell(Color.RED + "5. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void totalMember(MemberService memberService, Scanner input) {
        // Total Member as integer
        int totalMember = memberService.getAllMembers(1, Integer.MAX_VALUE).size();

        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.setColumnWidth(0, 35, 65);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Total Member" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + totalMember + Color.RESET, titleStyle);
        System.out.println(table.render());
        int page = 1;
        int pageSize = 5;
        int total = memberService.getAllMembers(1, Integer.MAX_VALUE).size();
        while (true) {
            System.out.println(Color.MAGENTA + "📋 Displaying members (Page " + page + ")" + Color.RESET);
            List<Member> allMembers = memberService.getAllMembers(page, pageSize);
            MemberViewUtil.displayMembersInTable(allMembers);
            System.out.print(Color.CYAN + "👉 Enter 'n' for next page, 'p' for previous page, 'b' to go back: " + Color.RESET);
            String ch = input.nextLine().trim().toLowerCase();
            if (ch.equals("n") && (page * pageSize) < total) {
                page++;
            } else if (ch.equals("p") && page > 1) {
                page--;
            } else if (ch.equals("b")) {
                break;
            } else {
                System.out.println(Color.RED + "⚠️ Invalid choice, Please try again" + Color.RESET);
            }
        }
    }

    public static void totalBorrow(BorrowService borrowService) {
        int totalBorrow = borrowService.getAllBorrowRecord().size();

        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.setColumnWidth(0, 35, 65);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Total Borrow" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + totalBorrow + Color.RESET, titleStyle);
        System.out.println(table.render());
    }

    public static void totalAvailableBook(BookService bookService) {
        int totalAvailableBook = 0;
        for (Book book : bookService.getAllBooks()) {
            totalAvailableBook += book.getQuantity();
        }
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.setColumnWidth(0, 35, 65);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Total Available" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + totalAvailableBook + Color.RESET, titleStyle);
        System.out.println(table.render());
    }

    public static void totalBook(BookService bookService, BorrowService borrowService) {
        int totalAvailableBook = 0;
        for (Book book : bookService.getAllBooks()) {
            totalAvailableBook += book.getQuantity();
        }
        int totalBook = borrowService.getAllBorrowRecord().size() + totalAvailableBook;
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.setColumnWidth(0, 35, 65);
        CellStyle titleStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Total Book (Available + Borrow)" + Color.RESET, titleStyle);
        table.addCell(Color.YELLOW + totalBook + Color.RESET, titleStyle);
        System.out.println(table.render());
    }

    public static void reportLoop(Scanner input, BookService bookService, MemberService memberService, BorrowService borrowService) {
        while (true) {
            BookView bookView = new BookView(bookService, new InputValidator(input), memberService, borrowService);
            reportMenu();
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-4): " + Color.RESET);
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    System.out.println(Color.YELLOW + "📚 You selected: Total Books" + Color.RESET);
                    totalBook(bookService, borrowService);
                    bookView.navigateTotalBookPagination();
                }
                case "2" -> {
                    System.out.println(Color.YELLOW + "👪 You selected: Total Members" + Color.RESET);
                    totalMember(memberService, input);
                }
                case "3" -> {
                    System.out.println(Color.YELLOW + "📚 You selected: Total Borrow" + Color.RESET);
                    totalBorrow(borrowService);
                    bookView.navigateBorrowPagination();
                    continue;
                }
                case "4" -> {
                    System.out.println(Color.YELLOW + "📕 You selected: Available Books" + Color.RESET);
                    totalAvailableBook(bookService);
                    bookView.navigatePagination();
                    continue;
                }
                case "5" -> {
                    System.out.println(Color.BOLD_CYAN + "🔙 Returning to Main Menu..." + Color.RESET);
                    return;
                }
                default -> {
                    System.out.println(Color.BOLD_CYAN + "Invalid choice. Please try again!" + Color.RESET);
                    continue;
                }
            }
            System.out.println(Color.YELLOW + "⚡️ Press ENTER to continue..." + Color.RESET);
            input.nextLine();
        }
    }
}