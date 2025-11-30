package co.istad.library.model;

import java.time.LocalDate;

public class BorrowRecord {
    private String memberName;
    private String bookName;
    private LocalDate borrowAt;
    private LocalDate willReturn;

    public BorrowRecord(String memberName, String bookName, LocalDate borrowAt, LocalDate willReturn) {
        this.memberName = memberName;
        this.bookName = bookName;
        this.borrowAt = borrowAt;
        this.willReturn = willReturn;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getBorrowAt() {
        return borrowAt;
    }

    public void setBorrowAt(LocalDate borrowAt) {
        this.borrowAt = borrowAt;
    }

    public LocalDate getWillReturn() {
        return willReturn;
    }

    public void setWillReturn(LocalDate willReturn) {
        this.willReturn = willReturn;
    }
}
