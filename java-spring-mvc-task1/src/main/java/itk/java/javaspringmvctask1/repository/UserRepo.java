package itk.java.javaspringmvctask1.repository;

import itk.java.javaspringmvctask1.entity.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    public Page<User> findAll(@NonNull Pageable pageable);
}
