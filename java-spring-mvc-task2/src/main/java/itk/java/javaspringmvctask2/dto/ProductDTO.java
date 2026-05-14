package itk.java.javaspringmvctask2.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private Double price;
}
