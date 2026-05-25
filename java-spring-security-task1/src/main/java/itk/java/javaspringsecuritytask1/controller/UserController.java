package itk.java.javaspringsecuritytask1.controller;

import itk.java.javaspringsecuritytask1.dto.AuthRequest;
import itk.java.javaspringsecuritytask1.dto.AuthResponse;
import itk.java.javaspringsecuritytask1.dto.RefreshTokenRequest;
import itk.java.javaspringsecuritytask1.dto.UserUpdateDTO;
import itk.java.javaspringsecuritytask1.entity.Users;
import itk.java.javaspringsecuritytask1.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest authRequest
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(
            @RequestBody AuthRequest authRequest){

        return ResponseEntity.ok(userService.registerNewUser(authRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(userService.getNewTokens(request));
    }

    @PostMapping("/unlock/{username}")
    @PreAuthorize("hasRole('ROOT')")
    public ResponseEntity<String> unlockUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.unlockUser(username));
    }

    @GetMapping("/viewUsers")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Page<Users>> viewAllUsers(Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @PatchMapping("/updateUser/{userId}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Users> updateUser(
            @PathVariable UUID userId,
            @Valid @RequestBody UserUpdateDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }

}
