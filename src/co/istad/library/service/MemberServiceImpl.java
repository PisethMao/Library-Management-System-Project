package co.istad.library.service;

import co.istad.library.model.Member;
import co.istad.library.model.MemberSortField;
import co.istad.library.model.MemberStatus;
import co.istad.library.util.Color;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    private final List<Member> members = new ArrayList<>();

    public MemberServiceImpl() {
        LocalDate membershipDate = LocalDate.of(2022, 1, 1);
        LocalDate expiryDate = LocalDate.of(2022, 12, 31);
        members.add(new Member("John", "123 Main St.", "123-456-7890", "pisethmao2002@gmail.com", membershipDate, expiryDate, "Gold", MemberStatus.ACTIVE));
        members.add(new Member("Jane", "456 Main St.", "987-654-3210", "menglongkeo07@gmail.com", membershipDate, expiryDate, "Silver", MemberStatus.EXPIRED));
        members.add(new Member("Bob", "789 Main St.", "098-765-4321", "chanchhay07@gmail.com", membershipDate, expiryDate, "Bronze", MemberStatus.INACTIVE));
        members.add(new Member("Alice", "101 Main St.", "111-222-3333", "kompheakyan@gmail.com", membershipDate, expiryDate, "Gold", MemberStatus.SUSPENDED));
        members.add(new Member("Charlie", "202 Main St.", "222-333-4444", "pisethmao2002@gmail.com", membershipDate, expiryDate, "Silver", MemberStatus.ACTIVE));
        members.add(new Member("David", "303 Main St.", "333-444-5555", "menglongkeo07@gmail.com", membershipDate, expiryDate, "Bronze", MemberStatus.ACTIVE));
    }

    @Override
    public void addMember(String name, String address, String phoneNumber, String email, LocalDate membershipDate, LocalDate expiryDate, String membershipType, MemberStatus status) {
        Member member = new Member(name, address, phoneNumber, email, membershipDate, expiryDate, membershipType, status);
        members.add(member);
        System.out.println(Color.BOLD_GREEN + "✅ Nem member added successfully! " + member.getName() + Color.RESET);
    }

    @Override
    public List<Member> getAllMembers(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, members.size());
        return members.subList(startIndex, endIndex);
    }

    @Override
    public boolean updateMember(String memberId, String newName, String newAddress, String newPhoneNumber, String newEmail, LocalDate newMembershipDate, LocalDate newExpiryDate, String newMembershipType, MemberStatus newStatus) {
        Optional<Member> memberOpt = members.stream().filter(m -> m.getId().equals(memberId)).findFirst();
        if (memberOpt.isEmpty()) {
            return false;
        }
        Member member = memberOpt.get();
        member.setName(newName);
        member.setAddress(newAddress);
        member.setPhoneNumber(newPhoneNumber);
        member.setEmail(newEmail);
        member.setMembershipDate(newMembershipDate);
        member.setExpiryDate(newExpiryDate);
        member.setMembershipType(newMembershipType);
        member.setStatus(newStatus);
        return true;
    }

    @Override
    public boolean removeMember(String memberId) {
        return members.removeIf(m -> m.getId().equals(memberId));
    }

    @Override
    public Optional<Member> searchMember(String memberId) {
        return members.stream().filter(m -> m.getId().equals(memberId)).findFirst();
    }

    @Override
    public List<Member> searchMembers(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        return members.stream()
                .filter(m ->
                        m.getName().toLowerCase().contains(lowerCaseKeyword) ||
                                m.getAddress().toLowerCase().contains(lowerCaseKeyword) ||
                                m.getPhoneNumber().toLowerCase().contains(lowerCaseKeyword) ||
                                m.getEmail().toLowerCase().contains(lowerCaseKeyword) ||
                                m.getMembershipType().toLowerCase().contains(lowerCaseKeyword) ||
                                m.getStatus().name().toLowerCase().contains(lowerCaseKeyword))
                .toList();
    }

    @Override
    public List<Member> getAllMembersSorted(MemberSortField sortField, boolean ascending) {
        Comparator<Member> comparator;
        switch (sortField) {
            case NAME -> comparator = Comparator.comparing(Member::getName, String.CASE_INSENSITIVE_ORDER);
            case MEMBERSHIP_DATE -> comparator = Comparator.comparing(Member::getMembershipDate);
            case EXPIRY_DATE -> comparator = Comparator.comparing(Member::getExpiryDate);
            case MEMBERSHIP_TYPE ->
                    comparator = Comparator.comparing(Member::getMembershipType, String.CASE_INSENSITIVE_ORDER);
            case STATUS -> comparator = Comparator.comparing(m -> m.getStatus().name(), String.CASE_INSENSITIVE_ORDER);
            default -> throw new IllegalArgumentException("Unknown sort field: " + sortField);
        }
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return members.stream().sorted(comparator).toList();
    }
}
