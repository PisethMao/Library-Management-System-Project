package co.istad.library.model;

import java.time.LocalDate;

public record BorrowRecord(String memberName, String bookName, String bookCategory, LocalDate borrowAt,
                           LocalDate willReturn) {
}
