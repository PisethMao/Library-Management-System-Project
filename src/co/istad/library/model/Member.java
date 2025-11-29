package co.istad.library.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Member {
    private final String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate membershipDate;
    private LocalDate expiryDate;
    private List<String> borrowedBooks;
    private double fines;
    private String membershipType;
    private MemberStatus status;

    public Member(String name, String address, String phoneNumber, String email, LocalDate membershipDate, LocalDate expiryDate, String membershipType, MemberStatus status) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipDate = membershipDate;
        this.expiryDate = expiryDate;
        this.membershipType = membershipType;
        this.status = status;
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

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
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

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    public void addBorrowedBook(String bookId) {
        borrowedBooks.add(bookId);
    }

    public void removeBorrowedBook(String bookId) {
        borrowedBooks.remove(bookId);
    }

    public void calculateFines() {

    }
}
