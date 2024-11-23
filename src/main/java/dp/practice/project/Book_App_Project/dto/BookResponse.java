package dp.practice.project.Book_App_Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private Integer quantity;
    private String genre;
}
