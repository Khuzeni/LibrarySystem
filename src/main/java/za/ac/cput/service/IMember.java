package za.ac.cput.service;

import za.ac.cput.domain.Member;

import java.util.List;

public interface IMember {
    Member create(Member member);
    Member read(Long memberId);
    Member update(Member member);
    boolean delete(Long memberId);
    List<Member> getAllMembers();
}

