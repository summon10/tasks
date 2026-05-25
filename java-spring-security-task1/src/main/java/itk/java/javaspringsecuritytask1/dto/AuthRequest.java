package itk.java.javaspringsecuritytask1.dto;

import itk.java.javaspringsecuritytask1.model.Role;
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private Role role;
}
