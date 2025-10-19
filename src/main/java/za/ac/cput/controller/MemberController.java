package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Member;
import za.ac.cput.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")

public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        System.out.println("GET /api/members called");
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        System.out.println("GET /api/members/" + id + " called");
        Member member = memberService.read(id);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        System.out.println("POST /api/members called with: " + member);
        return memberService.create(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        System.out.println("PUT /api/members/" + id + " called");
        Member existingMember = memberService.read(id);
        if (existingMember != null) {
            Member updatedMember = new Member.Builder()
                    .copy(existingMember)
                    .setName(memberDetails.getName())
                    .setEmail(memberDetails.getEmail())
                    .setPhoneNumber(memberDetails.getPhoneNumber())
                    .build();
            return ResponseEntity.ok(memberService.update(updatedMember));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        System.out.println("DELETE /api/members/" + id + " called");
        boolean deleted = memberService.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

