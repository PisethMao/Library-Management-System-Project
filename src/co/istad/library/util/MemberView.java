package co.istad.library.util;

import co.istad.library.model.Member;
import co.istad.library.service.MemberService;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public record MemberView(MemberService memberService, InputValidator inputValidator) {
    private static final int PAGE_SIZE = 5;
    private static int currentPage = 1;
    private Table buildPageTable(List<Member> members, int startIndex, int endIndex) {
        Table table = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("ID (UUID)", center);
        table.addCell("Name", center);
        table.addCell("Age", center);
        table.addCell("Gender", center);
        for (int i = startIndex; i < endIndex; i++) {
            Member member = members.get(i);
            table.addCell(member.getId(), center);
            table.addCell(member.getName(), center);
            table.addCell(String.valueOf(member.getAge()), center);
            table.addCell(member.getGender(), center);
        }
        return table;
    }

    public void addNewMember() {
        String name = inputValidator.readText(Color.GREEN + "📖 Enter member name: " + Color.RESET);
        int age = inputValidator.readInt(Color.GREEN + "📅 Enter member age: " + Color.RESET);
        String gender = inputValidator.readText(Color.GREEN + "✍️ Enter member gender: " + Color.RESET);
        Member newMember = new Member(name, age, gender);
        memberService.addMember(newMember);
        System.out.println(Color.BOLD_YELLOW + "✅ Member added successfully!" + Color.RESET);
    }

    public void displayMembersPage() {
        List<Member> members = memberService.getAllMember();
        int totalMembers = members.size();
        int totalPages = (int) Math.ceil((double) totalMembers / PAGE_SIZE);
        if (totalPages == 0) {
            System.out.println(Color.RED + "⚠️ No books available!" + Color.RESET);
            return;
        }
        if (currentPage > totalPages) currentPage = totalPages;
        int startIndex = (currentPage - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, totalMembers);
        Table table = buildPageTable(members, startIndex, endIndex);
        System.out.println(table.render());
        System.out.println(Color.BOLD_CYAN + "📄 Page " + currentPage + " of " + totalPages + Color.RESET);
        System.out.println(Color.BOLD_YELLOW + "Options: "
                + Color.GREEN + "1. Next Page "
                + Color.RESET + "| "
                + Color.RED + "2. Previous Page "
                + Color.RESET + "| "
                + Color.BOLD_RED + "3. Exit"
                + Color.RESET);
    }

    public void navigatePagination() {
        Scanner input = new Scanner(System.in);
        while (true) {
            displayMembersPage();
            System.out.print(Color.BOLD_CYAN + "👉 Type 1/2/3. Press ENTER to exit. " + Color.RESET);
            String line = input.nextLine().trim();
            int totalPages = (int) Math.ceil((double) memberService.getAllMember().size() / PAGE_SIZE);
            if (line.isEmpty()) {
                return;
            }
            switch (line) {
                case "1" -> currentPage = Math.min(currentPage + 1, totalPages);
                case "2" -> currentPage = Math.max(currentPage - 1, 1);
                case "3" -> {
                    return;
                }
                default -> System.out.println(Color.BOLD_RED + "⚠️ Invalid input. Use arrows or 1/2/3." + Color.RESET);
            }
        }
    }




}
