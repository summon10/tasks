package itk.java.javaspringmvctask1.controller;

import com.fasterxml.jackson.annotation.JsonView;
import itk.java.javaspringmvctask1.dto.UserDTO;
import itk.java.javaspringmvctask1.entity.User;
import itk.java.javaspringmvctask1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @JsonView(User.UserSummary.class)
    public ResponseEntity<Page<User>> getUsers(Pageable pageable)
    {
        Page<User> users = userService.findAllUsers(pageable);
        return ResponseEntity.ok(users);

    }

    @GetMapping("/users/{userId}")
    @JsonView(User.UserDetails.class)
    public ResponseEntity<User> getUserByIdWithOrderDetails(
            @Valid @PathVariable Long userId
            ){
       return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PostMapping("/users/createUser")
    public ResponseEntity<User> createUser(
            @Valid @RequestBody User user
    ){
        return ResponseEntity.ok(userService.createOrUpdateNewUser(user));
    }

   @PatchMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserDTO userDTO
    ){
       User existingUser = userService.findUserById(userId);

       existingUser.setEmail(userDTO.getEmail());
       existingUser.setName(userDTO.getName());
       existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        return ResponseEntity.ok(userService.createOrUpdateNewUser(existingUser));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Valid @PathVariable Long userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }



}
