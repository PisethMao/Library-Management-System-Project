package co.istad.library.util;

import co.istad.library.model.Member;

import java.util.List;

public class MemberViewUtil {
    public static void printMemberDetail(List<Member> members) {
        System.out.println(Color.BOLD_CYAN + "ID | Name | Email | Membership Type | Status" + Color.RESET);
        for (Member member : members) {
            System.out.println(member.getId() + " | " + member.getName() + " | " + member.getEmail() + " | " + member.getMembershipType() + " | " + member.getStatus());
        }
    }
}