package itk.java.javaspringmvctask1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @Size(min = 1)
    private String name;
    @Pattern(regexp="^\\+[1-9]\\d{1,14}$")
    private String phoneNumber;
    @Email
    private String email;

}
