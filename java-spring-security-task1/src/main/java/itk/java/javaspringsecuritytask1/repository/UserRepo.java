package itk.java.javaspringsecuritytask1.repository;

import itk.java.javaspringsecuritytask1.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<Users, UUID> {
    Optional<Users> findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.failedLoginAttempts = 0 WHERE u.username = :username")
    void resetFailedAttempts(String username);

    Optional<Users> findUserById(UUID id);
}
