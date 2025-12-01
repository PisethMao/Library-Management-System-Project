package co.istad.library.service;

import co.istad.library.model.BorrowRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    public final List<BorrowRecord> borrowRecords = new ArrayList<>();

    public BorrowServiceImpl() {
        borrowRecords.add(new BorrowRecord("John", "The Great Gatsby", LocalDate.now(), LocalDate.now().plusDays(7)));
    }

    public BorrowServiceImpl(String name, String bookTitle, LocalDate borrowDate, LocalDate returnDate) {
        borrowRecords.add(new BorrowRecord(name, bookTitle, borrowDate, returnDate));
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
                        r.getMemberName().equals(memberName)
                                && r.getBookName().equals(bookName)
                );
    }
}

