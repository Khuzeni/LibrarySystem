package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;
    private String email;
    private String phoneNumber;


    public Member() {
    }

    //Constructor with Builder
private Member(Builder builder) {
        this.memberId = builder.memberId;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;

}
    //Getters


    public Long getMemberId() {
        return memberId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Builder Pattern
    public static class Builder {
        private Long memberId;
        private String name;
        private String email;
        private String phoneNumber;

        public Builder setMemberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder copy(Member member) {
            this.memberId = member.memberId;
            this.name = member.name;
            this.email = member.email;
            this.phoneNumber = member.phoneNumber;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}