package co.istad.library;

import co.istad.library.service.*;
import co.istad.library.util.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BookService bookService = new BookServiceImpl();
        MemberService memberService = new MemberServiceImpl();
        InputValidator inputValidator = new InputValidator(input);
        BorrowService borrowService = new BorrowServiceImpl();
        ViewUtil.printBanner();
        while (true) {
            ViewUtil.showMainMenu();
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-5): " + Color.RESET);
            String choice = input.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println(Color.BOLD_CYAN + "📘 Book Management Selected" + Color.RESET);
                    ViewUtil.bookMenuLoop(input, bookService, memberService, borrowService);
                }
                case "2" -> {
                    System.out.println(Color.BOLD_GREEN + "🧑‍🤝‍🧑 You selected: Member Management" + Color.RESET);
                    MemberViewUtil.showMemberMenu(input, memberService, inputValidator);
                }
                case "3" -> {
                    System.out.println(Color.BOLD_YELLOW + "🔄️ You selected: Borrow & Return System" + Color.RESET);
                    ViewUtil.borrowBookLoop(input, bookService, memberService, borrowService);
                }
                case "4" -> {
                    System.out.println(Color.BOLD_BLUE + "📊 You selected: Reports & Dashboards" + Color.RESET);
                    ViewUtil.reportLoop(input, bookService, memberService, borrowService);
                }
                case "5" -> {
                    System.out.println(Color.BOLD_RED + "❌ Exiting the program... Goodbye!" + Color.RESET);
                    // The return statement in this code is used to exit the main method.
                    return;
                }
                default -> System.out.println(Color.RED + "⚠️ Invalid choice, Please try again" + Color.RESET);
            }
            System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
            input.nextLine();
        }
    }
}
