package itk.java.javaspringsecuritytask1.dto;

import itk.java.javaspringsecuritytask1.model.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDTO {
    @NotEmpty
    private String username;
    private Role role;
}
