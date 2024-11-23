package dp.practice.project.Book_App_Project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.*;

@Data
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class GenreResponse {
    private Long id;
    private String name;

    private String description;
}
