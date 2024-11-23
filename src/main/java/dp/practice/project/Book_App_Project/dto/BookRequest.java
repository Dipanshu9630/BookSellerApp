package dp.practice.project.Book_App_Project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BookRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    private Double price;

    private Integer quantity;

    @NotBlank
    private String genre;
}
