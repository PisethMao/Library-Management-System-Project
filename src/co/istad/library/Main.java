package co.istad.library;

import co.istad.library.service.BookService;
import co.istad.library.service.BookServiceImpl;
import co.istad.library.service.MemberService;
import co.istad.library.service.MemberServiceImpl;
import co.istad.library.util.Color;
import co.istad.library.util.InputValidator;
import co.istad.library.util.MemberViewUtil;
import co.istad.library.util.ViewUtil;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BookService bookService = new BookServiceImpl();
        MemberService memberService = new MemberServiceImpl();
        InputValidator inputValidator = new InputValidator(input);
        ViewUtil.printBanner();
        while (true) {
            ViewUtil.showMainMenu();
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-6): " + Color.RESET);
            String choice = input.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println(Color.BOLD_CYAN + "📘 Book Management Selected" + Color.RESET);
                    ViewUtil.bookMenuLoop(input, bookService);
                }
                case "2" -> {
                    System.out.println(Color.BOLD_GREEN + "🧑‍🤝‍🧑 You selected: Member Management" + Color.RESET);
                    MemberViewUtil.showMemberMenu(input, memberService, inputValidator);
                }
                case "3" -> {
                    System.out.println(Color.BOLD_YELLOW + "🔄️ You selected: Borrow & Return System" + Color.RESET);
                    ViewUtil.borrowBookLoop(input, bookService);
                }
                case "4" -> System.out.println(Color.BOLD_PURPLE + "📜 You selected: Activity Logs" + Color.RESET);
                case "5" -> System.out.println(Color.BOLD_BLUE + "📊 You selected: Reports & Dashboards" + Color.RESET);
                case "6" -> {
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
