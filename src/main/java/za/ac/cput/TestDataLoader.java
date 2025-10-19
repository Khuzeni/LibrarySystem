package za.ac.cput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Book;
import za.ac.cput.domain.Member;
import za.ac.cput.service.BookService;
import za.ac.cput.service.MemberService;

@Component
public class TestDataLoader implements CommandLineRunner {
    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @Override
    public void run(String... args) throws Exception {
        // Add some test books
        if (bookService.getAllBooks().isEmpty()) {
            Book book1 = new Book.Builder()
                    .setBookId(1L)
                    .setTitle("The Great Gatsby")
                    .setAuthor("F. Scott Fitzgerald")
                    .setIsAvailable(true)
                    .setIsbn("9780743273565")
                    .build();

            Book book2 = new Book.Builder()
                    .setBookId(2L)
                    .setTitle("To Kill a Mockingbird")
                    .setAuthor("Harper Lee")
                    .setIsAvailable(true)
                    .setIsbn("9780061120084")
                    .build();

            bookService.create(book1);
            bookService.create(book2);
            System.out.println("Test books added to database");
        }

        // Add some test members
        if (memberService.getAllMembers().isEmpty()) {
            Member member1 = new Member.Builder()
                    .setMemberId(1L)
                    .setName("John Doe")
                    .setEmail("john.doe@example.com")
                    .setPhoneNumber("(555) 123-4567")
                    .build();

            memberService.create(member1);
            System.out.println("Test members added to database");
        }
    }
}
