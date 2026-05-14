package itk.java.javaspringmvctask2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Order {

    @Id
    private Long orderId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @NotEmpty
    @OneToMany
    @JoinColumn(name = "productId")
    private List<Product> products;

    @NotNull
    private LocalDate orderDate;

    @NotEmpty
    private String shippingAddress;

    @NotNull
    private Double totalPrice;

    @NotEmpty
    private String orderStatus;

}
