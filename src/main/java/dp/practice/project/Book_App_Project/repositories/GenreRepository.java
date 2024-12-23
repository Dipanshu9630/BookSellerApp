package dp.practice.project.Book_App_Project.repositories;

import dp.practice.project.Book_App_Project.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    Optional<Genre> findByNameIgnoreCase(String name);
}
