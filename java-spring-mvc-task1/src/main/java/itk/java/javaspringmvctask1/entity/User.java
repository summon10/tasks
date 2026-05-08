package itk.java.javaspringmvctask1.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {

    public static class UserSummary {}
    public static class UserDetails extends UserSummary {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UserSummary.class)
    @NotNull
    private Long userId;

    @NotNull
    @JsonView(UserSummary.class)
    @NotBlank(message = "Name is empty")
    private String name;

    @Email
    @JsonView(UserDetails.class)
    private String email;

    @Pattern(regexp="^\\+[1-9]\\d{1,14}$")
    @JsonView(UserDetails.class)
    private String phoneNumber;

    @NotNull
    @JoinColumn (name = "orderId")
    @OneToMany
    @JsonView(UserDetails.class)
    private List<Order> orders;

}
