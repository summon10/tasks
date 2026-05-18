package itk.java.javaspringdatatask2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String position;

    @NotNull
    private Double salary;

    @OneToOne
    @JoinColumn(name = "departmentId")
    private Department department;

}
