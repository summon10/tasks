package itk.java.javaspringmvctask1.service;


import itk.java.javaspringmvctask1.entity.User;
import itk.java.javaspringmvctask1.exception.ResourceNotFoundException;
import itk.java.javaspringmvctask1.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public Page<User> findAllUsers(Pageable pageable){
        pageable = PageRequest.of(0, 10);

        return userRepo.findAll(pageable);

    }

    public User findUserById(long userId){
        return userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User doesn't exists"));

    }

    public User createOrUpdateNewUser(User user){
         return userRepo.save(user);
    }

    public void deleteById(Long userId) {
        if (userId == null) throw new ResourceNotFoundException("User doesn't exists");
        userRepo.deleteById(userId);
    }
}
