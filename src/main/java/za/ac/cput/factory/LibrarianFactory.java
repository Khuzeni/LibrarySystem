package za.ac.cput.factory;

import za.ac.cput.domain.Librarian;

public class LibrarianFactory {
    public static Librarian createLibrarian(Long librarianId, String firstName,String email) {
        // Validation
        if (librarianId == null) {
            throw new IllegalArgumentException("Librarian ID cannot be null or empty");
        }
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
//        if (lastName == null || lastName.isEmpty()) {
//            throw new IllegalArgumentException("Last name cannot be null or empty");
//        }
//        if (phoneNumber == null || phoneNumber.isEmpty()) {
//            throw new IllegalArgumentException("Phone number cannot be null or empty");
//        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        return new Librarian.Builder()
                .setLibrarianId(librarianId)
                .setFirstName(firstName)
                .setEmail(email)
                .build();
    }
}
