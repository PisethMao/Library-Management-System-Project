package co.istad.library.service;

import co.istad.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private final List<Book> books = new ArrayList<>();

    public BookServiceImpl() {
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Novel", "1234567890", 1925, 10));
        books.add(new Book("1984", "George Orwell", "Dystopian", "0987654321", 1949, 5));
        books.add(new Book("The Lord of the Rings", "J. R. R. Tolkien", "Novel", "978-0-345-33322-2", 1954, 10));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Novel", "1234567890", 1925, 10));
        books.add(new Book("1984", "George Orwell", "Dystopian", "0987654321", 1949, 5));
        books.add(new Book("The Lord of the Rings", "J. R. R. Tolkien", "Novel", "978-0-345-33322-2", 1954, 10));
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }


    @Override
    public Book findId(String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id.trim())) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void update(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equalsIgnoreCase(updatedBook.getId())) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @Override
    public boolean delete(String id) {
        return books.removeIf(book -> book.getId().equalsIgnoreCase(id.trim()));
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList();
    }

    @Override
    public List<Book> searchByCategory(String category) {
        return books.stream()
                .filter(b -> b.getCategory().toLowerCase().contains(category.toLowerCase()))
                .toList();
    }

    @Override
    public List<Book> searchByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().contains(isbn))
                .toList();
    }
}
