package co.istad.library.service;

import co.istad.library.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    private final List<Member> members = new ArrayList<>();

    public MemberServiceImpl() {
        members.add(new Member("1", "John Doe", "1234 Elm St", "123-456-7890", "john.doe@example.com",
                "2021-01-01", "2023-01-01", "Regular", "Active"));
        members.add(new Member("2", "Jane Smith", "5678 Oak St", "987-654-3210", "jane.smith@example.com",
                "2021-02-01", "2023-02-01", "Premium", "Active"));
        members.add(new Member("3", "Alice Johnson", "1357 Pine St", "555-555-5555", "alice.johnson@example.com",
                "2021-03-01", "2023-03-01", "Student", "Suspended"));
        members.add(new Member("4", "Bob Brown", "2468 Maple St", "777-777-7777", "bob.brown@example.com",
                "2021-04-01", "2023-04-01", "Regular", "Active"));
        members.add(new Member("5", "Charlie Davis", "1359 Birch St", "888-888-8888", "charlie.davis@example.com",
                "2021-05-01", "2023-05-01", "Premium", "Active"));
        members.add(new Member("6", "Deborah White", "2460 Cedar St", "999-999-9999", "deborah.white@example.com",
                "2021-06-01", "2023-06-01", "Regular", "Inactive"));
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
        System.out.println("Nem member added successfully! " + member.getName());
    }

    @Override
    public List<Member> getAllMembers(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, members.size());
        return members.subList(startIndex, endIndex);
    }

    @Override
    public boolean updateMember(String memberId, String newName, String newAddress) {
        Optional<Member> memberOpt = members.stream().filter(m -> m.getId().equals(memberId)).findFirst();
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            member.setName(newName);
            member.setAddress(newAddress);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeMember(String memberId) {
        return members.removeIf(m -> m.getId().equals(memberId));
    }

    @Override
    public Optional<Member> searchMember(String memberId) {
        return members.stream().filter(m -> m.getId().equals(memberId)).findFirst();
    }
}
