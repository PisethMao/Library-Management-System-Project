package co.istad.library.model;

import java.util.UUID;

public class Book {
    private final String id;
    private String title;
    private String author;
    private String category;
    // ISBN = International Standard Book Number
    // I use isbn because uniquely identify a book edition
    private String isbn;
    private int year;
    private int quantity;

    public Book(String title, String author, String category, String isbn, int year, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.year = year;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
