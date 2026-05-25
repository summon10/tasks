package itk.java.javaspringsecuritytask1.service;

import io.jsonwebtoken.Claims;
import itk.java.javaspringsecuritytask1.dto.AuthRequest;
import itk.java.javaspringsecuritytask1.dto.AuthResponse;
import itk.java.javaspringsecuritytask1.dto.RefreshTokenRequest;
import itk.java.javaspringsecuritytask1.dto.UserUpdateDTO;
import itk.java.javaspringsecuritytask1.entity.Users;
import itk.java.javaspringsecuritytask1.exception.ResourceNotFoundException;
import itk.java.javaspringsecuritytask1.model.UserPrincipal;
import itk.java.javaspringsecuritytask1.repository.UserRepo;
import itk.java.javaspringsecuritytask1.util.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JWTUtil jwtUtil;

    public Users registerNewUser(AuthRequest request){
        log.info("New registration attempt: '{}'", request.getUsername());

        Users user = new Users();
       user.setUsername(request.getUsername());
       user.setPassword(encoder.encode(request.getPassword()));
       user.setRole(request.getRole());
       user.setIsAccountNonLocked(true);
        log.info("User '{}' successfully registered [{}]", user.getUsername(), user.getRole());
       return userRepo.save(user);
    }

    public AuthResponse login(AuthRequest request){
        log.info("User try to login: '{}'", request.getUsername());
        String accessToken = null;
        String refreshToken = null;
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Users user = userRepo.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

            accessToken = jwtUtil.generateAccessToken(user);
            refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
        log.info("Successful login: '{}'", user.getUsername());
        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse getNewTokens(RefreshTokenRequest request){
        log.info("Request for new tokens");
        String refreshToken = request.getRefreshToken();
        jwtUtil.validateRefreshToken(refreshToken);
        Claims claims = jwtUtil.getClaims(refreshToken, jwtUtil.getRefreshSigningKey());
        String username = claims.getSubject();
        Users user = userRepo.findUserByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        if (user.getIsAccountNonLocked() == false) {
            log.warn("Token renew impossible, account '{}' locked", username);
            throw new LockedException("User blocked");
        }
        return new AuthResponse(jwtUtil.generateAccessToken(user), jwtUtil.generateRefreshToken(user.getUsername()));
    }


    public String unlockUser(String username) {

        Users user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        user.setIsAccountNonLocked(true);
        user.setFailedLoginAttempts(0);
        userRepo.save(user);
        log.info("User '{}' unlocked", username);
        return (username + " Successfully unlocked");
    }

    public Page<Users> getAllUsers(Pageable pageable) {
        pageable = PageRequest.of(0,10);
        return userRepo.findAll(pageable);
    }

    public Users updateUser(UUID userId, @Valid UserUpdateDTO userDTO) {

       Users existingUser = userRepo.findUserById(userId)
               .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
       existingUser.setUsername(userDTO.getUsername());
       existingUser.setRole((userDTO.getRole()));
       return userRepo.save(existingUser);
    }
}
