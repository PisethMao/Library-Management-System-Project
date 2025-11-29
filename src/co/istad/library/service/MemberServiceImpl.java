package co.istad.library.service;

import co.istad.library.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    private final List<Member> members = new ArrayList<>();
    public MemberServiceImpl() {
        members.add(new Member("John", "123 Main St.", "123-456-7890", "pisethmao2002@gmail.com", "2022-01-01", "2022-12-31", "Gold", "Active"));
        members.add(new Member("Jane", "456 Main St.", "987-654-3210", "menglongkeo07@gmail.com", "2022-01-01", "2022-12-31", "Silver", "Active"));
        members.add(new Member("Bob", "789 Main St.", "098-765-4321", "chanchhay07@gmail.com", "2022-01-01", "2022-12-31", "Bronze", "Active"));
        members.add(new Member("Alice", "101 Main St.", "111-222-3333", "kompheakyan@gmail.com", "2022-01-01", "2022-12-31", "Gold", "Active"));
        members.add(new Member("Charlie", "202 Main St.", "222-333-4444", "pisethmao2002@gmail.com", "2022-01-01", "2022-12-31", "Silver", "Active"));
        members.add(new Member("David", "303 Main St.", "333-444-5555", "menglongkeo07@gmail.com", "2022-01-01", "2022-12-31", "Bronze", "Active"));
    }

    @Override
    public void addMember(String name, String address, String phoneNumber, String email, String membershipDate, String expiryDate, String membershipType, String status) {
        Member member = new Member(name, address, phoneNumber, email, membershipDate, expiryDate, membershipType, status);
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
