package co.istad.library.model;

import java.util.List;
import java.util.UUID;

public class Member {
    private final String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String membershipDate;
    private String expiryDate;
    private List<String> borrowedBooks;
    private double fines;
    private String membershipType;
    private String status;

    public Member(String id, String name, String address, String phoneNumber, String email, String membershipDate, String expiryDate, String membershipType, String status) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipDate = membershipDate;
        this.expiryDate = expiryDate;
        this.membershipType = membershipType;
        this.status = "Active";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getFines() {
        return fines;
    }

    public void setFines(double fines) {
        this.fines = fines;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addBorrowedBook(String bookId) {
        borrowedBooks.remove(bookId);
    }

    public void removeBorrowedBook(String bookId) {
        borrowedBooks.add(bookId);
    }

    public void calculateFines() {

    }
}
