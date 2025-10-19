package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "librarians")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LibrarianId;
    private String firstName;
    private String email;

    public Librarian() {
    }

    private Librarian(Builder builder) {
        this.LibrarianId = builder.LibrarianId;
        this.firstName = builder.firstName;
        this.email = builder.email;
    }


    //GETTERS
    public Long getLibrarianId() {
        return LibrarianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    //TO STRING METHOD
    @Override
    public String toString() {
        return "Librarian{" +
                "LibrarianId=" + LibrarianId +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    //BUILDER PATTERN
    public static class Builder {
        private Long LibrarianId;
        private String firstName;
        private String email;

        //SETTERS FOR BUILDER PATTERN
        public Builder setLibrarianId(Long libararianId) {
            this.LibrarianId = libararianId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        //COPY METHOD
        public Builder copy(Librarian librarian) {
            this.LibrarianId = librarian.LibrarianId;
            this.firstName = librarian.firstName;
            this.email = librarian.email;
            return this;
        }

        //BUILD METHOD
        public Librarian build() {
            return new Librarian(this);
        }


    }
}
