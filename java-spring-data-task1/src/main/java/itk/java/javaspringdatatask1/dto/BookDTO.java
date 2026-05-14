package itk.java.javaspringdatatask1.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class BookDTO {
    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private Year year;
}
