package co.istad.library.util;

import co.istad.library.model.Member;
import co.istad.library.model.MemberSortField;
import co.istad.library.model.MemberStatus;
import co.istad.library.service.MemberService;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MemberViewUtil {
    private MemberViewUtil() {
    }

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
            table.addCell(Color.BOLD_GREEN + member.getId() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getName() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getAddress() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getPhoneNumber() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getEmail() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getMembershipDate().toString() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getExpiryDate().toString() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getMembershipType() + Color.RESET, header);
            table.addCell(Color.BOLD_GREEN + member.getStatus().toString() + Color.RESET, header);
        }
        System.out.println(table.render());
    }

    private static Table getTable() {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        CellStyle header = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Member Management" + Color.RESET, header);
        table.addCell(Color.BOLD_YELLOW + "1. Register New Member" + Color.RESET);
        table.addCell(Color.BOLD_YELLOW + "2. Update Member Info" + Color.RESET);
        table.addCell(Color.BOLD_YELLOW + "3. Remove Member" + Color.RESET);
        table.addCell(Color.BOLD_YELLOW + "4. Search Member" + Color.RESET);
        table.addCell(Color.BOLD_YELLOW + "5. Display all Members" + Color.RESET);
        table.addCell(Color.BOLD_YELLOW + "6. Sort Members" + Color.RESET);
        table.addCell(Color.BOLD_RED + "7. Back to Main Menu" + Color.RESET);
        return table;
    }

    private static Table getSortFieldTable() {
        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle header = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Option" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Sort By" + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "1." + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "Name" + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "2." + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "Membership Date" + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "3." + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "Expiry Date" + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "4." + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "Membership Type" + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "5." + Color.RESET, header);
        table.addCell(Color.BOLD_GREEN + "Status" + Color.RESET, header);
        return table;
    }

    private static Table getSortOrderTable() {
        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle header = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell(Color.BOLD_CYAN + "Option" + Color.RESET, header);
        table.addCell(Color.BOLD_CYAN + "Order" + Color.RESET, header);
        table.addCell(Color.BOLD_YELLOW + "1." + Color.RESET, header);
        table.addCell(Color.BOLD_YELLOW + "Ascending (A–Z / Oldest first)" + Color.RESET, header);
        table.addCell(Color.BOLD_YELLOW + "2." + Color.RESET, header);
        table.addCell(Color.BOLD_YELLOW + "Descending (Z–A / Newest first)" + Color.RESET, header);
        return table;
    }

    public static void showMemberMenu(Scanner input, MemberService memberService, InputValidator inputValidator) {
        while (true) {
            Table table = getTable();
            System.out.println(table.render());
            System.out.print(Color.BOLD_CYAN + "👉 Enter your choice (1-7): " + Color.RESET);
            String inputChoice = input.nextLine();
            switch (inputChoice) {
                case "1" -> {
                    System.out.println(Color.BOLD_GREEN + "📝 Registering new member..." + Color.RESET);
                    String name = inputValidator.readText(Color.BOLD_CYAN + "👤 Enter name: " + Color.RESET);
                    String address = inputValidator.readAddress(Color.BOLD_CYAN + "🏠 Enter address: " + Color.RESET);
                    String phoneNumber = inputValidator.readPhone(Color.BOLD_CYAN + "📞 Enter phone number: " + Color.RESET);
                    String email = inputValidator.readEmail(Color.BOLD_CYAN + "✉️ Enter email: " + Color.RESET);
                    LocalDate membershipDate = LocalDate.now();
                    LocalDate expiryDate = LocalDate.now().plusYears(10);
                    String membershipType;
                    while (true) {
                        membershipType = inputValidator.readText(
                                Color.BOLD_CYAN + "🥇 Enter membership type (Bronze, Silver, Gold): " + Color.RESET
                        );
                        membershipType = membershipType.trim().toLowerCase();
                        String normalized = membershipType.substring(0, 1).toUpperCase() + membershipType.substring(1);
                        if (normalized.equals("Bronze") || normalized.equals("Gold") || normalized.equals("Silver")) {
                            membershipType = normalized;
                            break;
                        }
                        System.out.println(Color.RED + "❌ Invalid Membership type!" + Color.RESET);
                    }
                    memberService.addMember(name, address, phoneNumber, email, membershipDate, expiryDate, membershipType, MemberStatus.ACTIVE);
                }

                case "2" -> {
                    System.out.println(Color.BOLD_YELLOW + "✏️ Updating member info..." + Color.RESET);
                    System.out.print(Color.BOLD_CYAN + "🔎 Enter member ID to update: " + Color.RESET);
                    String memberId = inputValidator.input().nextLine().trim();
                    Optional<Member> memberOpt = memberService.searchMember(memberId);
                    if (memberOpt.isEmpty()) {
                        System.out.println(Color.RED + "❌ Member not found!" + Color.RESET);
                        System.out.println(Color.YELLOW + "⚡ Press ENTER to try again..." + Color.RESET);
                        input.nextLine();   // wait
                        break;
                    }
                    Member m = memberOpt.get();
                    String newName = inputValidator.readOptionalText(m.getName(), Color.BOLD_CYAN + "👤 New name");
                    String newAddress = inputValidator.readOptionalText(m.getAddress(), Color.BOLD_CYAN + "🏠 New address");
                    String newPhone = inputValidator.readPhone(Color.BOLD_CYAN + "📞 New phone (" + m.getPhoneNumber() + "): ");
                    String newEmail = inputValidator.readEmail(Color.BOLD_CYAN + "✉️ New email (" + m.getEmail() + "): ");
                    LocalDate newMembershipDate = inputValidator.readDate(Color.BOLD_CYAN + "📅 New membership date (" + m.getMembershipDate() + "): ");
                    LocalDate newExpiryDate = inputValidator.readExpiryDate(Color.BOLD_CYAN + "⏰ New expiry date (" + m.getExpiryDate() + "): ", m.getMembershipDate());
                    String newType = inputValidator.readText(Color.BOLD_CYAN + "🥇 New membership type (" + m.getMembershipType() + "): ");
                    MemberStatus newStatus = inputValidator.readStatus(Color.BOLD_CYAN + "✅ New status (" + m.getStatus() + "): " + Color.RESET);
                    boolean updated = memberService.updateMember(memberId, newName, newAddress, newPhone, newEmail, newMembershipDate, newExpiryDate, newType, newStatus);
                    if (updated) {
                        System.out.println(Color.BOLD_GREEN + "✅ Member updated successfully!" + Color.RESET);
                    } else {
                        System.out.println(Color.RED + "❌ Failed to update member!" + Color.RESET);
                    }
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                }

                case "3" -> {
                    System.out.println(Color.BOLD_PURPLE + "❌ Removing member..." + Color.RESET);
                    System.out.print(Color.BOLD_CYAN + "🔎 Enter member ID to remove: " + Color.RESET);
                    String memberId = input.nextLine().trim();
                    Optional<Member> memberOpt = memberService.searchMember(memberId);
                    if (memberOpt.isEmpty()) {
                        System.out.println(Color.RED + "❌ Member not found!" + Color.RESET);
                        System.out.println(Color.YELLOW + "⚡ Press ENTER to try again..." + Color.RESET);
                        input.nextLine();      // wait
                        break;                 // back to Member Menu (NOT main)
                    }
                    Member m = memberOpt.get();
                    // Show a quick summary before deleting
                    System.out.println(Color.BOLD_YELLOW + "🧾 Member found:" + Color.RESET);
                    System.out.println(Color.BOLD_GREEN + "ID: " + m.getId());
                    System.out.println(Color.BOLD_GREEN + "Name: " + m.getName());
                    System.out.println(Color.BOLD_GREEN + "Email: " + m.getEmail());
                    System.out.println(Color.BOLD_GREEN + "Status: " + m.getStatus() + Color.RESET);
                    System.out.print(Color.BOLD_RED + "⚠️ Are you sure you want to delete this member? (y/n): " + Color.RESET);
                    String confirm = input.nextLine().trim().toLowerCase();
                    if (confirm.equals("y")) {
                        boolean removed = memberService.removeMember(memberId);
                        if (removed) {
                            System.out.println(Color.BOLD_GREEN + "✅ Member deleted successfully!" + Color.RESET);
                        } else {
                            System.out.println(Color.RED + "❌ Failed to delete member!" + Color.RESET);
                        }
                    } else {
                        System.out.println(Color.YELLOW + "↩️ Delete cancelled." + Color.RESET);
                    }
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                }

                case "4" -> {
                    System.out.println(Color.BOLD_BLUE + "🔍 Searching member..." + Color.RESET);
                    System.out.print(Color.BOLD_CYAN + "🔎 Enter keyword (name, email, phone, address, type, status): " + Color.RESET);
                    String keyword = input.nextLine().trim();
                    if (keyword.isEmpty()) {
                        System.out.println(Color.RED + "❌ Search keyword cannot be empty!" + Color.RESET);
                        System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                        input.nextLine();
                        break;
                    }
                    List<Member> results = memberService.searchMembers(keyword);
                    if (results.isEmpty()) {
                        System.out.println(Color.RED + "❌ No members found for keyword: " + keyword + Color.RESET);
                    } else {
                        System.out.println(Color.BOLD_GREEN + "✅ Found " + results.size() + " member(s):" + Color.RESET);
                        displayMembersInTable(results);
                    }
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                }

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
                            System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                            input.nextLine();
                        }
                    }
                }
                case "6" -> {
                    System.out.println(Color.BOLD_GREEN + "📊 Sorting members..." + Color.RESET);
                    Table sortFieldTable = getSortFieldTable();
                    System.out.println(sortFieldTable.render());
                    int sortChoice = inputValidator.readInt(Color.BOLD_CYAN + "👉 Choose sort field (1-5): " + Color.RESET);
                    MemberSortField field = null;
                    switch (sortChoice) {
                        case 1 -> field = MemberSortField.NAME;
                        case 2 -> field = MemberSortField.MEMBERSHIP_DATE;
                        case 3 -> field = MemberSortField.EXPIRY_DATE;
                        case 4 -> field = MemberSortField.MEMBERSHIP_TYPE;
                        case 5 -> field = MemberSortField.STATUS;
                        default -> System.out.println(Color.RED + "❌ Invalid sort field!" + Color.RESET);
                    }
                    if (field == null) {
                        System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                        input.nextLine();
                        break;   // break out of case "6", stay in showMemberMenu
                    }
                    Table sortOrderTable = getSortOrderTable();
                    System.out.println(sortOrderTable.render());
                    int orderChoice;
                    while (true) {
                        orderChoice = inputValidator.readInt(Color.BOLD_CYAN + "👉 Choose order (1-2): " + Color.RESET);
                        if (orderChoice == 1 || orderChoice == 2) {
                            break;
                        }
                        System.out.println(Color.RED + "❌ Invalid order! Please choose 1 or 2." + Color.RESET);
                    }
                    boolean ascending = (orderChoice == 1);
                    List<Member> sorted = memberService.getAllMembersSorted(field, ascending);
                    if (sorted.isEmpty()) {
                        System.out.println(Color.RED + "❌ No members to display." + Color.RESET);
                        System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                        input.nextLine();
                        break;
                    }
                    int page = 1;
                    int pageSize = 5;
                    int total = sorted.size();
                    label:
                    while (true) {
                        int startIndex = (page - 1) * pageSize;
                        int endIndex = Math.min(startIndex + pageSize, total);
                        System.out.println(Color.MAGENTA + "📋 Sorted members (Page " + page + ")" + Color.RESET);
                        List<Member> pageMembers = sorted.subList(startIndex, endIndex);
                        displayMembersInTable(pageMembers);
                        System.out.print(Color.CYAN + "👉 Enter 'n' for next page, 'p' for previous page, 'b' to go back: " + Color.RESET);
                        String nav = input.nextLine().trim().toLowerCase();
                        switch (nav) {
                            case "n":
                                if (endIndex >= total) {
                                    System.out.println(Color.RED + "⚠️ You are on the last page." + Color.RESET);
                                } else {
                                    page++;
                                }
                                break;
                            case "p":
                                if (page == 1) {
                                    System.out.println(Color.RED + "⚠️ You are on the first page." + Color.RESET);
                                } else {
                                    page--;
                                }
                                break;
                            case "b":
                                System.out.println(Color.YELLOW + "⚡ Returning to Member Menu..." + Color.RESET);
                                break label;
                            default:
                                System.out.println(Color.RED + "❌ Invalid choice!" + Color.RESET);
                                break;
                        }
                    }
                }
                case "7" -> {
                    System.out.println(Color.BOLD_RED + "🔙 Returning to Main Menu..." + Color.RESET);
                    return;
                }
                default -> {
                    System.out.println(Color.RED + "⚠️ Invalid choice, Please try again" + Color.RESET);
                    System.out.println(Color.YELLOW + "⚡ Press ENTER to continue..." + Color.RESET);
                    input.nextLine();
                }
            }
        }
    }
}