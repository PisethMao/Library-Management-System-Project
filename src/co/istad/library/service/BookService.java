package co.istad.library.service;

import co.istad.library.model.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getAllBooks();

    // I use isEmpty() function in book service because we must check the book list has no books before we try to display a table.
//    boolean isEmpty();

    Book findId(String id);

    void update(Book updatedBook);

    boolean delete(String id);

    List<Book> searchByTitle(String title);

    List<Book> searchByAuthor(String author);

    List<Book> searchByCategory(String category);

    List<Book> searchByIsbn(String isbn);

    List<Book> sortBooksByTitleAsc();

    List<Book> sortBooksByTitleDesc();

    List<Book> sortBooksByAuthorAsc();

    List<Book> sortBooksByAuthorDesc();

    List<Book> sortBooksByCategoryAsc();

    List<Book> sortBooksByCategoryDesc();

    List<Book> sortBooksByPublishYearAsc();

    List<Book> sortBooksByPublishYearDesc();
}
