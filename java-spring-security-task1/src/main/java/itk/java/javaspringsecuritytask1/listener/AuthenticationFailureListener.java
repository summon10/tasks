package itk.java.javaspringsecuritytask1.listener;


import itk.java.javaspringsecuritytask1.entity.Users;
import itk.java.javaspringsecuritytask1.exception.ResourceNotFoundException;
import itk.java.javaspringsecuritytask1.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final UserRepo userRepo;
    @Value("${jwt.max-failed-attempts:5}")
    private  int MAX_FAILED_ATTEMPTS;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = event.getAuthentication().getName();
          Users user = userRepo.findUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username + (" Not Found")));
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        user.setLastFailedLogin(LocalDateTime.now());
        log.warn("Login failed '{}'. Attepmts: {} of {}",
                username, user.getFailedLoginAttempts(), MAX_FAILED_ATTEMPTS);
        if (user.getFailedLoginAttempts() >= MAX_FAILED_ATTEMPTS) {
            user.setIsAccountNonLocked(false);
            log.error("User locked '{}'", username);
        }

        userRepo.save(user);
    }
}
