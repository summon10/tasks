package itk.java.javaspringsecuritytask1.entity;

import itk.java.javaspringsecuritytask1.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    private Boolean isAccountNonLocked;

    private Integer failedLoginAttempts;
    private LocalDateTime lastFailedLogin;

}
