package co.istad.library.util;

import co.istad.library.model.Book;
import co.istad.library.model.BorrowRecord;
import co.istad.library.model.Member;
import co.istad.library.service.BookService;
import co.istad.library.service.BorrowService;
import co.istad.library.service.MemberService;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public record BookView(BookService bookService, InputValidator inputValidator, MemberService memberService,
                       BorrowService borrowService) {
    private static final int PAGE_SIZE = 5;
    private static int currentPage = 1;

    private static Table getTable() {
        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.CYAN + "SORT OPTIONS" + Color.RESET, center, 2);
        table.addCell(Color.GREEN + "1. Title (A → Z)" + Color.RESET);
        table.addCell(Color.GREEN + "2. Title (Z → A)" + Color.RESET);
        table.addCell(Color.YELLOW + "3. Author (A → Z)" + Color.RESET);
        table.addCell(Color.YELLOW + "4. Author (Z → A)" + Color.RESET);
        table.addCell(Color.YELLOW + "5. Category (A → Z)" + Color.RESET);
        table.addCell(Color.YELLOW + "6. Category (Z → A)" + Color.RESET);
        table.addCell(Color.PURPLE + "7. Year (Newest → Oldest)" + Color.RESET);
        table.addCell(Color.PURPLE + "8. Year (Oldest → Newest)" + Color.RESET);
        table.addCell(Color.RED + "9. Back to Book Menu" + Color.RESET, center, 2);
        return table;
    }

    private Table buildPageTable(List<Book> books, int startIndex, int endIndex) {
        Table table = new Table(7, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD + "ID (UUID)" + Color.RESET, center);
        table.addCell(Color.BOLD + "Title" + Color.RESET, center);
        table.addCell(Color.BOLD + "Author" + Color.RESET, center);
        table.addCell(Color.BOLD + "Category" + Color.RESET, center);
        table.addCell(Color.BOLD + "ISBN" + Color.RESET, center);
        table.addCell(Color.BOLD + "Publish Year" + Color.RESET, center);
        table.addCell(Color.BOLD + "Quantity" + Color.RESET, center);
        for (int i = startIndex; i < endIndex; i++) {
            Book b = books.get(i);
            table.addCell(Color.BOLD_GREEN + b.getId() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getTitle() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getAuthor() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getCategory() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getIsbn() + Color.RESET, center);
            // I use String.valueOf() because the table only accepts String objects.
            table.addCell(Color.BOLD_GREEN + b.getYear() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getQuantity() + Color.RESET, center);
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
        System.out.println(Color.BOLD_YELLOW + "Options: " + Color.GREEN + "1. Next Page " + Color.RESET + "| " + Color.BOLD_BLUE + "2. Previous Page " + Color.RESET + "| " + Color.BOLD_RED + "3. Exit" + Color.RESET);
    }

    public void navigatePagination() {
        Scanner input = new Scanner(System.in);
        while (true) {
            displayBooksPage();
            System.out.print(Color.BOLD_CYAN + "👉 Type 1/2/3. Press ENTER to exit. Enter: " + Color.RESET);
            String line = input.nextLine().trim();
            int totalPages = (int) Math.ceil((double) bookService.getAllBooks().size() / PAGE_SIZE);
            if (line.isEmpty()) {
                return;
            }
            switch (line) {
                case "1" -> currentPage = Math.min(currentPage + 1, totalPages);
                case "2" -> currentPage = Math.max(currentPage - 1, 1);
                case "3" -> {
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                    return;
                }
                default -> {
                    System.out.println(Color.BOLD_RED + "⚠️ Invalid input. Use 1/2/3." + Color.RESET);
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                }
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

    public void updateBookInfo() {
        System.out.print(Color.BOLD_CYAN + "🔍 Enter Book ID to update: " + Color.RESET);
        String id = inputValidator.input().nextLine().trim();
        Book existing = bookService.findId(id);
        if (existing == null) {
            System.out.println(Color.BOLD_RED + "❌ Book not found with ID: " + id + Color.RESET);
            return;
        }
        System.out.println(Color.GREEN + "✅ Found: " + existing.getTitle() + Color.RESET);
        System.out.println(Color.YELLOW + "(Press ENTER to keep the old value)" + Color.RESET);
        String newTitle = inputValidator.readOptionalText(existing.getTitle(), Color.GREEN + "📖 Title ");
        String newAuthor = inputValidator.readOptionalText(existing.getAuthor(), Color.GREEN + "✍️ Author ");
        String newCategory = inputValidator.readOptionalText(existing.getCategory(), Color.GREEN + "📚️ Category ");
        String newIsbn = inputValidator.readOptionalIsbn(existing.getIsbn(), Color.GREEN + "🔢 ISBN ");
        int newYear = inputValidator.readOptionalInt(existing.getYear(), Color.GREEN + "📅 Publish year ");
        int newQty = inputValidator.readOptionalInt(existing.getQuantity(), Color.GREEN + "📦 Quantity ");
        existing.setTitle(newTitle);
        existing.setAuthor(newAuthor);
        existing.setCategory(newCategory);
        existing.setIsbn(newIsbn);
        existing.setYear(newYear);
        existing.setQuantity(newQty);
        bookService.update(existing);
        System.out.println(Color.GREEN + "✅ Book updated successfully!" + Color.RESET);
    }

    public void deleteBook() {
        System.out.print(Color.CYAN + "🗑️ Enter Book ID to delete: " + Color.RESET);
        String id = inputValidator.input().nextLine().trim();
        Book existing = bookService.findId(id);
        if (existing == null) {
            System.out.println(Color.BOLD_RED + "❌ No book found with ID: " + id + Color.RESET);
            return;
        }
        System.out.println(Color.YELLOW + "⚠️ You are about to delete this book: " + Color.RESET);
        System.out.println(Color.BOLD_CYAN + "📖 Title: " + Color.RESET + existing.getTitle());
        System.out.println(Color.BOLD_CYAN + "✍️ Author: " + Color.RESET + existing.getAuthor());
        System.out.println(Color.BOLD_CYAN + "📚️ Category: " + Color.RESET + existing.getCategory());
        System.out.println(Color.BOLD_CYAN + "🔢 ISBN: " + Color.RESET + existing.getIsbn());
        System.out.println(Color.BOLD_CYAN + "📅 Year: " + Color.RESET + existing.getYear());
        System.out.println(Color.BOLD_CYAN + "📦 Quantity: " + Color.RESET + existing.getQuantity() + Color.RESET);
        System.out.print(Color.RED + "❗Type YES to confirm delete: " + Color.RESET);
        Set<String> ok = Set.of("yes", "y");
        String confirm = inputValidator.input().nextLine().trim();
        if (!ok.contains(confirm.toLowerCase())) {
            System.out.println(Color.YELLOW + "❎ Delete cancelled." + Color.RESET);
            return;
        }
        boolean deleted = bookService.delete(id);
        if (deleted) {
            System.out.println(Color.BOLD_GREEN + "✅ Book deleted successfully!" + Color.RESET);
        } else {
            System.out.println(Color.RED + "❌ Failed to delete book." + Color.RESET);
        }
    }

    public void displaySearchResults(List<Book> results) {
        Table table = new Table(7, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle header = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "ID (UUID)" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Title" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Author" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Category" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "ISBN" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Publish Year" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Quantity" + Color.RESET, header);
        for (Book b : results) {
            table.addCell(Color.BOLD_BLUE + b.getId() + Color.RESET, header);
            table.addCell(Color.BOLD_BLUE + b.getTitle() + Color.RESET, header);
            table.addCell(Color.BOLD_BLUE + b.getAuthor() + Color.RESET, header);
            table.addCell(Color.BOLD_BLUE + b.getCategory() + Color.RESET, header);
            table.addCell(Color.BOLD_BLUE + b.getIsbn() + Color.RESET, header);
            table.addCell(Color.BOLD_BLUE + b.getYear() + Color.RESET, header);
            table.addCell(Color.BOLD_BLUE + b.getQuantity() + Color.RESET, header);
        }
        System.out.println(table.render());
    }

    public void searchBooks() {
        while (true) {
            System.out.println(Color.CYAN + "🔍 Search Books" + Color.RESET);
//            ViewUtil.showSearchMenu();
            System.out.print(Color.YELLOW + "👉 Enter your choice (1-5): " + Color.RESET);
            String choice = inputValidator.input().nextLine().trim();
            List<Book> results;
            switch (choice) {
                case "1" -> {
                    String title = inputValidator.readText(Color.GREEN + "📖 Enter title to search: " + Color.RESET);
                    results = bookService.searchByTitle(title);
                }
                case "2" -> {
                    String author = inputValidator.readText(Color.GREEN + "✍️ Enter author to search: " + Color.RESET);
                    results = bookService.searchByAuthor(author);
                }
                case "3" -> {
                    String category = inputValidator.readText(Color.GREEN + "📚️ Enter category to search: " + Color.RESET);
                    results = bookService.searchByCategory(category);
                }
                case "4" -> {
                    String isbn = inputValidator.readIsbn(Color.GREEN + "🔢 Enter ISBN to search: " + Color.RESET);
                    results = bookService.searchByIsbn(isbn);
                }
                case "5" -> {
                    System.out.println(Color.BOLD_CYAN + "🔙 Back to Book Menu" + Color.RESET);
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    inputValidator.input().nextLine();
                    return;
                }
                default -> {
                    System.out.println(Color.BOLD_RED + "⚠️ Invalid choice." + Color.RESET);
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    inputValidator.input().nextLine();
                    continue;
                }
            }
            if (results.isEmpty()) {
                System.out.println(Color.RED + "❌ No book founds." + Color.RESET);
                System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                inputValidator.input().nextLine();
                continue;
            }
            displaySearchResults(results);
            System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
            inputValidator.input().nextLine();
        }
    }

    private List<Book> showSortMenu() {
        Scanner input = inputValidator.input();
        while (true) {
            Table table = getTable();
            System.out.println(table.render());
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-9): " + Color.RESET);
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    return bookService.sortBooksByTitleAsc();
                }
                case "2" -> {
                    return bookService.sortBooksByTitleDesc();
                }
                case "3" -> {
                    return bookService.sortBooksByAuthorAsc();
                }
                case "4" -> {
                    return bookService.sortBooksByAuthorDesc();
                }
                case "5" -> {
                    return bookService.sortBooksByCategoryAsc();
                }
                case "6" -> {
                    return bookService.sortBooksByCategoryDesc();
                }
                case "7" -> {
                    return bookService.sortBooksByPublishYearDesc();
                }
                case "8" -> {
                    return bookService.sortBooksByPublishYearAsc();
                }
                case "9" -> {
                    return null;
                }
                default -> System.out.println(Color.BOLD_RED + "⚠️ Invalid choice." + Color.RESET);
            }
        }
    }

    public void paginateSortedBooks(List<Book> sortedBooks) {
        int totalBooks = sortedBooks.size();
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);
        if (totalPages == 0) {
            System.out.println(Color.RED + "⚠️ No books available!" + Color.RESET);
            return;
        }
        Scanner input = inputValidator.input();
        while (true) {
            int startIndex = (currentPage - 1) * PAGE_SIZE;
            int endIndex = Math.min(startIndex + PAGE_SIZE, totalBooks);
            Table table = buildPageTable(sortedBooks, startIndex, endIndex);
            System.out.println(table.render());
            System.out.println(Color.BOLD_CYAN + "📄 Page " + currentPage + " of " + totalPages + Color.RESET);
            System.out.println(Color.BOLD_YELLOW + "1. Next Page | 2. Previous Page | 3. Change Sort | 4. Exit" + Color.RESET);
            System.out.print(Color.GREEN + "👉 Enter your choice: " + Color.RESET);
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1" -> currentPage = Math.min(currentPage + 1, totalPages);
                case "2" -> currentPage = Math.max(currentPage - 1, 1);
                case "3" -> {
                    currentPage = 1;
                    sortedBooks = showSortMenu();
                    if (sortedBooks == null) {
                        return;
                    }
                    totalBooks = sortedBooks.size();
                    totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);
                }
                case "4" -> {
                    return;
                }
                default -> System.out.println(Color.RED + "⚠️ Invalid input." + Color.RESET);
            }
        }
    }

    public void displaySortedBooks() {
        List<Book> sortedBooks = showSortMenu();
        if (sortedBooks == null) {
            return;
        }
        currentPage = 1;
        paginateSortedBooks(sortedBooks);
    }

    public Member memberVerify() {
        System.out.print(Color.BOLD_CYAN + "🔎 Enter member ID to continue: " + Color.RESET);
        String memberId = inputValidator.input().nextLine().trim();
        Optional<Member> memberOpt = memberService.searchMember(memberId);
        if (memberOpt.isEmpty()) {
            System.out.println(Color.RED + "❌ Member not found!" + Color.RESET);
            return null;
        }
        Member m = memberOpt.get();
        System.out.println(Color.BOLD_YELLOW + "🧾 Member found:" + Color.RESET);
        System.out.println(Color.BOLD_GREEN + "ID: " + m.getId());
        System.out.println(Color.BOLD_GREEN + "Name: " + m.getName());
        System.out.println(Color.BOLD_GREEN + "Email: " + m.getEmail());
        System.out.println(Color.BOLD_GREEN + "Status: " + m.getStatus() + Color.RESET);
        System.out.print(Color.BOLD_RED + "⚠️ Are you sure you want to continue as this member? (y/n): " + Color.RESET);
        String confirm = inputValidator.input().nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            return m;
        } else {
            System.out.println(Color.BOLD_RED + "❌ Cancelled verification");
            return null;
        }
    }

    public void borrowBook(Member memberData) {
        System.out.print(Color.CYAN + "🗑️ Enter Book ID to borrow: " + Color.RESET);
        String id = inputValidator.input().nextLine().trim();
        Book existing = bookService.findId(id);
        if (existing == null) {
            System.out.println(Color.BOLD_RED + "❌ No book found with ID: " + id + Color.RESET);
            return;
        }
        if (existing.getQuantity() == 0) {
            System.out.println(Color.BOLD_RED + "Book Out Of Stock !!");
            return;
        }
        if (borrowService.hasBorrow(memberData.getName(), existing.getTitle())) {
            System.out.println(Color.BOLD_RED + "Cannot borrow the same book!!" + Color.RESET);
            return;
        }
        System.out.println(Color.YELLOW + "⚠️ You are about to borrow this book: " + Color.RESET);
        System.out.println(Color.BOLD_CYAN + "📖 Title: " + Color.RESET + existing.getTitle());
        System.out.println(Color.BOLD_CYAN + "✍️ Author: " + Color.RESET + existing.getAuthor());
        System.out.println(Color.BOLD_CYAN + "📚️ Category: " + Color.RESET + existing.getCategory());
        System.out.println(Color.BOLD_CYAN + "🔢 ISBN: " + Color.RESET + existing.getIsbn());
        System.out.println(Color.BOLD_CYAN + "📅 Year: " + Color.RESET + existing.getYear());
        System.out.println(Color.BOLD_CYAN + "📦 Quantity: " + Color.RESET + existing.getQuantity() + Color.RESET);
        System.out.print(Color.RED + "❗Type YES to confirm borrow: " + Color.RESET);
        Set<String> ok = Set.of("yes", "y");
        String confirm = inputValidator.input().nextLine().trim();
        if (!ok.contains(confirm.toLowerCase())) {
            System.out.println(Color.YELLOW + "❎ Borrow cancelled." + Color.RESET);
            return;
        }
        existing.setQuantity(existing.getQuantity() - 1);
        if (existing.getQuantity() >= 0) {
            BorrowRecord newBorrowRecord = new BorrowRecord(memberData.getName(), existing.getTitle(), LocalDate.now(), LocalDate.now().plusDays(7));
            borrowService.addBorrowRecord(newBorrowRecord);
            System.out.println(Color.BOLD_GREEN + "✅ Book borrowed successfully!" + Color.RESET);
        } else {
            System.out.println(Color.RED + "❌ Failed to borrow book." + Color.RESET);
        }
    }

    public void displayBorrowRecord() {
        List<BorrowRecord> borrowRecords = borrowService.getAllBorrowRecord();
        int totalBooks = borrowRecords.size();
        int totalPages = (int) Math.ceil((double) totalBooks / PAGE_SIZE);
        if (totalPages == 0) {
            System.out.println(Color.RED + "⚠️ No records available!" + Color.RESET);
            return;
        }
        if (currentPage > totalPages) currentPage = totalPages;
        int startIndex = (currentPage - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, totalBooks);
        Table table = buildBorrowTable(borrowRecords, startIndex, endIndex);
        System.out.println(table.render());
        System.out.println(Color.BOLD_CYAN + "📄 Page " + currentPage + " of " + totalPages + Color.RESET);
        System.out.println(Color.BOLD_YELLOW + "Options: " + Color.GREEN + "1. Next Page " + Color.RESET + "| " + Color.BOLD_BLUE + "2. Previous Page " + Color.RESET + "| " + Color.BOLD_RED + "3. Exit" + Color.RESET);
    }

    private Table buildBorrowTable(List<BorrowRecord> borrowRecords, int startIndex, int endIndex) {
        Table table = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD + "Member Name" + Color.RESET, center);
        table.addCell(Color.BOLD + "Book Title" + Color.RESET, center);
        table.addCell(Color.BOLD + "Borrow Date" + Color.RESET, center);
        table.addCell(Color.BOLD + "Return Date" + Color.RESET, center);
        for (int i = startIndex; i < endIndex; i++) {
            BorrowRecord b = borrowRecords.get(i);
            table.addCell(Color.BOLD_GREEN + b.getMemberName() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getBookName() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getBorrowAt() + Color.RESET, center);
            table.addCell(Color.BOLD_GREEN + b.getWillReturn() + Color.RESET, center);
        }
        return table;
    }

    public void navigateBorrowPagination() {
        Scanner input = new Scanner(System.in);
        while (true) {
            displayBorrowRecord();
            System.out.print(Color.BOLD_CYAN + "👉 Type 1/2/3. Press ENTER to exit. Enter: " + Color.RESET);
            String line = input.nextLine().trim();
            int totalPages = (int) Math.ceil((double) borrowService.getAllBorrowRecord().size() / PAGE_SIZE);
            if (line.isEmpty()) {
                return;
            }
            switch (line) {
                case "1" -> currentPage = Math.min(currentPage + 1, totalPages);
                case "2" -> currentPage = Math.max(currentPage - 1, 1);
                case "3" -> {
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                    return;
                }
                default -> {
                    System.out.println(Color.BOLD_RED + "⚠️ Invalid input. Use 1/2/3." + Color.RESET);
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                }
            }
        }
    }
}

