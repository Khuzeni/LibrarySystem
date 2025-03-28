package za.ac.cput.service;

import za.ac.cput.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    private MemberService memberService;
    private Member member;

    @BeforeEach
    void setUp() {
        memberService = new MemberService();
        member = new Member("M001", "John Doe", "john@example.com", "0712345678");
        memberService.registerMember(member);
    }

    @Test
    void testGetMember() {
        assertEquals(member, memberService.getMember("M001"));

    }
}
