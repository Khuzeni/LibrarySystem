package za.ac.cput.domain;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;

    public Member(String memberId, String name, String email, String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String toString() {
        return "Member{" + "memberId='" + memberId + '\'' + ", name='" + name + '\'' +
                ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + '}';
    }
}
