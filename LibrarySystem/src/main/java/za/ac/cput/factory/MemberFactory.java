package za.ac.cput.factory;

import za.ac.cput.domain.Member;

public class MemberFactory {
        public static Member createMember(String memberId, String name, String email, String phoneNumber) {
        return new Member(memberId, name, email, phoneNumber);
    }
}
