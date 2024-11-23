package dp.practice.project.Book_App_Project.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor


public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}

