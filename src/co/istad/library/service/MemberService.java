package co.istad.library.service;

import co.istad.library.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void addMember(Member member);
    List<Member> getAllMembers(int page, int pageSize);
    boolean updateMember(String memberId, String newName, String newAddress);
    boolean removeMember(String memberId);
    // Can have null and non-null
    // Introduced in Java 8
    // It is a public final class
    // It handled NullPointerException
    // It represents to object that have or don't have value
    Optional<Member> searchMember(String memberId);
}
