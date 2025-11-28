package co.istad.library.service;

import co.istad.library.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService{
    private final List<Member> members = new ArrayList<>();

    public MemberServiceImpl(){
        members.add(new Member("Chanchhay", 18, "male"));
        members.add(new Member("Piseth", 30, "male"));
        members.add(new Member("Melong", 40, "male"));
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
    }

    @Override
    public List<Member> getAllMember() {
        return members;
    }
}
