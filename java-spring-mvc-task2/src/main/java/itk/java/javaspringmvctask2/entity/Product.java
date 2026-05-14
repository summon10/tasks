package itk.java.javaspringmvctask2.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    private Long productid;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantityInStock;
}
