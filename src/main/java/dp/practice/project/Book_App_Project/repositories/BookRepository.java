package dp.practice.project.Book_App_Project.repositories;

import dp.practice.project.Book_App_Project.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByTitleIgnoreCase(String title);
    List<Books> findByGenreNameIgnoreCase(String genreName);
}
