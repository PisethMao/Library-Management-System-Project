package co.istad.library.service;

import co.istad.library.model.Member;

import java.util.List;

public interface MemberService {
    void addMember(Member member);
    List<Member> getAllMember();
}
