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
    public boolean isEmpty() {
        return books.isEmpty();
    }
}
