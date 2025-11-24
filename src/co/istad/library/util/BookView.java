package co.istad.library.util;

import co.istad.library.model.Book;
import co.istad.library.service.BookService;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

/**
 * @param bookService I comment on these two lines because I want BookView class to use the SAME Scanner and the SAME BookService that are created in Main, instead of creating new ones.
 *                    This prevents duplicate object and makes the book list work correctly.
 *                    private final Scanner input = new Scanner(System.in);
 *                    private final BookService bookService = new BookService();
 *                    I use record in this because it can reduce a boilerplate code.
 *                    I don't need to manually define getter methods, constructors, ...
 */
public record BookView(BookService bookService, InputValidator inputValidator) {
    private static final int PAGE_SIZE = 5;
    private static int currentPage = 1;

    private Table buildPageTable(List<Book> books, int startIndex, int endIndex) {
        Table table = new Table(7, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("ID (UUID)", center);
        table.addCell("Title", center);
        table.addCell("Author", center);
        table.addCell("Category", center);
        table.addCell("ISBN", center);
        table.addCell("Publish Year", center);
        table.addCell("Quantity", center);
        for (int i = startIndex; i < endIndex; i++) {
            Book b = books.get(i);
            table.addCell(b.getId(), center);
            table.addCell(b.getTitle(), center);
            table.addCell(b.getAuthor(), center);
            table.addCell(b.getCategory(), center);
            table.addCell(b.getIsbn(), center);
            // I use String.valueOf() because the table only accepts String objects.
            table.addCell(String.valueOf(b.getYear()), center);
            table.addCell(String.valueOf(b.getQuantity()), center);
        }
        return table;
    }

    public void displayBooksPage() {
        List<Book> books = bookService.getAllBooks();
        int totalBooks = books.size();
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);
        if (totalPages == 0) {
            System.out.println(Color.RED + "⚠️ No books available!" + Color.RESET);
            return;
        }
        if (currentPage > totalPages) currentPage = totalPages;
        int startIndex = (currentPage - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, totalBooks);
        Table table = buildPageTable(books, startIndex, endIndex);
        System.out.println(table.render());
        System.out.println(Color.BOLD_CYAN + "📄 Page " + currentPage + " of " + totalPages + Color.RESET);
        System.out.println(Color.BOLD_YELLOW + "Options: "
                + Color.GREEN + "1. Next Page "
                + Color.RESET + "| "
                + Color.RED + "2. Previous Page "
                + Color.RESET + "| "
                + Color.BOLD_RED + "3. Exit"
                + Color.RESET);
    }

    public void navigatePagination() {
        Scanner input = new Scanner(System.in);
        while (true) {
            displayBooksPage();
            System.out.print(Color.BOLD_CYAN + "👉 Use ← → arrows OR type 1/2/3. Press ENTER to exit. " + Color.RESET);
            String line = input.nextLine().trim();
            int totalPages = (int) Math.ceil((double) bookService.getAllBooks().size() / PAGE_SIZE);
            if (line.isEmpty()) {
                return;
            }
            switch (line) {
                case "1" -> currentPage = Math.min(currentPage + 1, totalPages);
                case "2" -> currentPage = Math.max(currentPage - 1, 1);
                case "3" -> {
                    return;
                }
                default -> System.out.println(Color.BOLD_RED + "⚠️ Invalid input. Use arrows or 1/2/3." + Color.RESET);
            }
        }
    }

    public void addNewBook() {
        String title = inputValidator.readText(Color.GREEN + "📖 Enter book title: " + Color.RESET);
        String author = inputValidator.readText(Color.GREEN + "✍️ Enter author name: " + Color.RESET);
        String category = inputValidator.readText(Color.GREEN + "✍️ Enter category: " + Color.RESET);
        String isbn = inputValidator.readIsbn(Color.GREEN + "🔢 Enter ISBN: " + Color.RESET);
        int year = inputValidator.readInt(Color.GREEN + "📅 Enter publish year: " + Color.RESET);
        int quantity = inputValidator.readInt(Color.GREEN + "📦 Enter quantity: " + Color.RESET);
        Book newBook = new Book(title, author, category, isbn, year, quantity);
        bookService.addBook(newBook);
        System.out.println(Color.BOLD_YELLOW + "✅ Book added successfully!" + Color.RESET);
    }
}
