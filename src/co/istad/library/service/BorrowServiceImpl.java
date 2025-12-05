package co.istad.library.service;

import co.istad.library.model.BorrowRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    private final List<BorrowRecord> borrowRecords = new ArrayList<>();

    public BorrowServiceImpl() {
        borrowRecords.add(new BorrowRecord("John", "The Great Gatsby", "Novel", LocalDate.now(), LocalDate.now().plusDays(7)));
        borrowRecords.add(new BorrowRecord("Charlie", "The Great Gatsby", "Novel", LocalDate.now(), LocalDate.now().plusDays(7)));
        borrowRecords.add(new BorrowRecord("John", "Pride and Prejudice", "Romance", LocalDate.now(), LocalDate.now().plusDays(7)));
        borrowRecords.add(new BorrowRecord("David", "The Great Gatsby", "Novel", LocalDate.now(), LocalDate.now().plusDays(7)));
        borrowRecords.add(new BorrowRecord("David", "War and Peace", "Historical", LocalDate.now(), LocalDate.now().plusDays(7)));
        borrowRecords.add(new BorrowRecord("Charlie", "War and Peace", "Historical", LocalDate.now(), LocalDate.now().plusDays(7)));
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecord() {
        return borrowRecords;
    }

    @Override
    public void addBorrowRecord(BorrowRecord borrowRecord) {
        borrowRecords.add(borrowRecord);
    }

    @Override
    public boolean hasBorrow(String memberName, String bookName) {
        return borrowRecords.stream()
                .anyMatch(r ->
                        r.memberName().equals(memberName)
                                && r.bookName().equals(bookName)
                );
    }

    @Override
    public int borrowCount(String memberName) {
        return (int) borrowRecords.stream()
                .filter(r ->
                        r.memberName().equals(memberName)
                ).count();
    }

    @Override
    public void returnBook(String memberName, String bookName) {
        borrowRecords.removeIf(bookRecord -> bookRecord.memberName().equalsIgnoreCase(memberName) && bookRecord.bookName().equalsIgnoreCase(bookName));
    }
}

