package za.ac.cput.factory;

import za.ac.cput.domain.Member;
import za.ac.cput.util.Helper;

public class MemberFactory {
    public static Member createMember(Long memberId, String name, String email, String phoneNumber) {
        if (Helper.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (Helper.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (Helper.isNullOrEmpty(phoneNumber)) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        return new Member.Builder()
                .setMemberId(memberId)
                .setName(name)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .build();
    }

}
