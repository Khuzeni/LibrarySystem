package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import za.ac.cput.domain.Loan;
import za.ac.cput.domain.Loan;
import za.ac.cput.domain.Member;
import za.ac.cput.repository.MemberRepository;

import java.util.List;

@Service
public class MemberService implements IMember {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member create(Member member) {
        // Check if member with same ID already exists
        if (member.getMemberId() != null && memberRepository.existsById(member.getMemberId())) {
            throw new IllegalArgumentException("Member ID already exists: " + member.getMemberId());
        }
        return memberRepository.save(member);
    }

    @Override
    public Member read(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member update(Member member) {
        if (member.getMemberId() == null || !memberRepository.existsById(member.getMemberId())) {
            throw new IllegalArgumentException("Member not found with ID: " + member.getMemberId());
        }
        return memberRepository.save(member);
    }

    @Override
    public boolean delete(Long memberId) {
        if (memberRepository.existsById(memberId)) {
            memberRepository.deleteById(memberId);
            return true;
        }
        return false;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Additional business logic methods
    public int countActiveLoans(Long memberId) {
        // Implement logic to count active loans for a member
        // This would typically call a LoanService
        return 0; // Placeholder
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}