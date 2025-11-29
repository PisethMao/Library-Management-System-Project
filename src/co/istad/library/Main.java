package co.istad.library;

import co.istad.library.model.Member;
import co.istad.library.service.BookService;
import co.istad.library.service.BookServiceImpl;
import co.istad.library.service.MemberService;
import co.istad.library.service.MemberServiceImpl;
import co.istad.library.util.Color;
import co.istad.library.util.InputValidator;
import co.istad.library.util.ViewUtil;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void displayMembersInTable(List<Member> members) {
        Table table = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle header = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "ID (UUID)" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Name" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Address" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Phone Number" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Email" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Membership Date" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Expiry Date" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Membership Type" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Status" + Color.RESET, header);
        for (Member member : members) {
            table.addCell(member.getId(), header);
            table.addCell(member.getName(), header);
            table.addCell(member.getAddress(), header);
            table.addCell(member.getPhoneNumber(), header);
            table.addCell(member.getEmail(), header);
            table.addCell(member.getMembershipDate(), header);
            table.addCell(member.getExpiryDate(), header);
            table.addCell(member.getMembershipType(), header);
            table.addCell(member.getStatus(), header);
        }
        System.out.println(table.render());
    }

    private static Table getTable() {
        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle header = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Option" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Description" + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "1." + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "Register New Member" + Color.RESET, header);
        table.addCell(Color.BOLD_YELLOW + "2." + Color.RESET, header);
        table.addCell(Color.BOLD_YELLOW + "Update Member Info" + Color.RESET, header);
        table.addCell(Color.BOLD_PURPLE + "3." + Color.RESET, header);
        table.addCell(Color.BOLD_PURPLE + "Remove Member" + Color.RESET, header);
        table.addCell(Color.BOLD_BLUE + "4." + Color.RESET, header);
        table.addCell(Color.BOLD_BLUE + "Search Member" + Color.RESET, header);
        table.addCell(Color.BOLD_MAGENTA + "5." + Color.RESET, header);
        table.addCell(Color.BOLD_MAGENTA + "Display all Members" + Color.RESET, header);
        table.addCell(Color.BOLD_RED + "6." + Color.RESET, header);
        table.addCell(Color.BOLD_RED + "Back to Main Menu" + Color.RESET, header);
        return table;
    }

    public static void showMemberMenu(Scanner input, MemberService memberService, InputValidator inputValidator) {
        while (true) {
            Table table = getTable();
            System.out.println(table.render());
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-6): " + Color.RESET);
            String inputChoice = input.nextLine();
            switch (inputChoice) {
                case "1" -> {
                    System.out.println(Color.BOLD_GREEN + "📝 Registering new member..." + Color.RESET);
                    String name = inputValidator.readText(Color.BOLD_CYAN + "👤 Enter name: " + Color.RESET);
                    String address = inputValidator.readText(Color.BOLD_CYAN + "🏠 Enter address: " + Color.RESET);
                    String phoneNumber = inputValidator.readText(Color.BOLD_CYAN + "📞 Enter phone number: " + Color.RESET);
                    String email = inputValidator.readText(Color.BOLD_CYAN + "✉️ Enter email: " + Color.RESET);
                    String membershipDate = inputValidator.readText(Color.BOLD_CYAN + "📅 Enter membership date (YYYY-MM-DD): " + Color.RESET);
                    String expiryDate = inputValidator.readText(Color.BOLD_CYAN + "⏰ Enter expiry date (YYYY-MM-DD): " + Color.RESET);
                    String membershipType = inputValidator.readText(Color.BOLD_CYAN + "🥇 Enter membership type (e.g., Regular, Gold, Platinum): " + Color.RESET);
                    String status = inputValidator.readText(Color.BOLD_CYAN + "✅ Enter status (e.g., Active, Inactive): " + Color.RESET);
                    memberService.addMember(name, address, phoneNumber, email, membershipDate, expiryDate, membershipType, status);
                }

                case "2" -> System.out.println(Color.BOLD_YELLOW + "✏️ Updating member info..." + Color.RESET);

                case "3" -> System.out.println(Color.BOLD_PURPLE + "❌ Removing member..." + Color.RESET);

                case "4" -> System.out.println(Color.BOLD_BLUE + "🔍 Searching member..." + Color.RESET);

                case "5" -> {
                    int page = 1;
                    int pageSize = 5;
                    int total = memberService.getAllMembers(1, Integer.MAX_VALUE).size();
                    while (true) {
                        System.out.println(Color.MAGENTA + "📋 Displaying members (Page " + page + ")" + Color.RESET);
                        List<Member> allMembers = memberService.getAllMembers(page, pageSize);
                        displayMembersInTable(allMembers);
                        System.out.print(Color.CYAN + "👉 Enter 'n' for next page, 'p' for previous page, 'b' to go back: " + Color.RESET);
                        String choice = input.nextLine().trim().toLowerCase();
                        if (choice.equals("n") && (page * pageSize) < total) {
                            page++;
                        } else if (choice.equals("p") && page > 1) {
                            page--;
                        } else if (choice.equals("b")) {
                            System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                            input.nextLine();
                            break;
                        } else {
                            System.out.println(Color.RED + "⚠️ Invalid choice, Please try again" + Color.RESET);
                        }
                    }
                }
                case "6" -> {
                    System.out.println(Color.BOLD_RED + "🔙 Returning to Main Menu..." + Color.RESET);
                    return;
                }
                default -> System.out.println(Color.RED + "⚠️ Invalid choice, Please try again" + Color.RESET);
            }
        }
    }

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
                    showMemberMenu(input, memberService, inputValidator);
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
