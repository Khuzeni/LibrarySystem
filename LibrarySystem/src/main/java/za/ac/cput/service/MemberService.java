package za.ac.cput.service;

import za.ac.cput.domain.Member;
import java.util.HashMap;
import java.util.Map;

public class MemberService {
    private Map<String, Member> members = new HashMap<>();

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }
}
