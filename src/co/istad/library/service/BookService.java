package co.istad.library.service;

import co.istad.library.model.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getAllBooks();

    // I use isEmpty() function in book service because we must check the book list has no books before we try to display a table.
    boolean isEmpty();
}
