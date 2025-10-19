package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Custom query methods
    Member findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.name LIKE %:name%")
    List<Member> findByNameContaining(String name);

    // Check if email already exists (for validation)
    boolean existsByEmail(String email);
}