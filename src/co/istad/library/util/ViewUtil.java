package co.istad.library.util;

import co.istad.library.service.BookService;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

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
        table.addCell(Color.BOLD_RED + "6. Back to Main Menu" + Color.RESET);
        System.out.println(table.render());
    }

    public static void bookMenuLoop(Scanner input, BookService bookService) {
        BookView bookView = new BookView(bookService, new InputValidator(input));
        while (true) {
            showBookMenu();
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-6): " + Color.RESET);
            String choice = input.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println(Color.BOLD_GREEN + "➕ Add New Books" + Color.RESET);
                    bookView.addNewBook();
                }
                case "2" ->
                        System.out.println(Color.BOLD_BLUE + "✏️ Update Book Information (no logic yet)" + Color.RESET);
                case "3" -> System.out.println(Color.BOLD_RED + "🗑️ Delete Book (no logic yet)" + Color.RESET);
                case "4" -> System.out.println(Color.BOLD_PURPLE + "🔍 Search Books (no logic yet)" + Color.RESET);
                case "5" -> {
                    System.out.println(Color.BOLD_YELLOW + "📄 Display/List All Books" + Color.RESET);
                    bookView.navigatePagination();
                    continue;
                }
                case "6" -> {
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
