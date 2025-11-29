package co.istad.library.service;

import co.istad.library.model.Member;
import co.istad.library.model.MemberSortField;
import co.istad.library.model.MemberStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    void addMember(String name, String address, String phoneNumber, String email, LocalDate membershipDate, LocalDate expiryDate, String membershipType, MemberStatus status);

    List<Member> getAllMembers(int page, int pageSize);

    boolean updateMember(String memberId, String newName, String newAddress, String newPhoneNumber, String newEmail, LocalDate newMembershipDate, LocalDate newExpiryDate, String newMembershipType, MemberStatus newStatus);

    boolean removeMember(String memberId);

    // Can have null and non-null
    // Introduced in Java 8
    // It is a public final class
    // It handled NullPointerException
    // It represents to object that have or don't have value
    Optional<Member> searchMember(String memberId);
    List<Member> searchMembers(String keyword);
    List<Member> getAllMembersSorted(MemberSortField sortField, boolean ascending);
}
