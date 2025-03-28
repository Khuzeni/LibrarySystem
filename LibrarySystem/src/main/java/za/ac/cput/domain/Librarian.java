package za.ac.cput.domain;

public class Librarian {
    private String librarianId;
    private String name;
    private String email;

    public Librarian(String librarianId, String name, String email) {
        this.librarianId = librarianId;
        this.name = name;
        this.email = email;
    }

    public String getLibrarianId() { return librarianId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Librarian{" + "librarianId='" + librarianId + '\'' + ", name='" + name + '\'' +
                ", email='" + email + '\'' + '}';
    }
}
