package co.istad.library.service;

import co.istad.library.model.BorrowRecord;

import java.util.List;

public interface BorrowService {
    List<BorrowRecord> getAllBorrowRecord();
    void addBorrowRecord(BorrowRecord borrowRecord);
    boolean hasBorrow(String memberName, String bookName);
    int borrowCount(String memberName);
    void returnBook(String memberName, String bookName);
}
