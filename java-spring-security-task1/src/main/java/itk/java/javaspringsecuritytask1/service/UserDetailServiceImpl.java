package itk.java.javaspringsecuritytask1.service;

import itk.java.javaspringsecuritytask1.entity.Users;
import itk.java.javaspringsecuritytask1.model.UserPrincipal;
import itk.java.javaspringsecuritytask1.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Users user = userRepo.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username + (" Not Found")));

        return new UserPrincipal(user);
    }
}
