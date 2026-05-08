package itk.java.javaspringmvctask1.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Order {

    @Id
    private Long orderId;

    @NotNull
    private List<String> goods;
    @NotNull @Positive
    private double summ;
    @NotNull
    private String status;
}
