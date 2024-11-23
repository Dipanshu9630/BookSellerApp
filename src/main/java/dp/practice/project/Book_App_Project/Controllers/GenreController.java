package dp.practice.project.Book_App_Project.Controllers;

import dp.practice.project.Book_App_Project.Services.GenreService;
import dp.practice.project.Book_App_Project.dto.GenreRequest;
import dp.practice.project.Book_App_Project.dto.GenreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public GenreResponse addGenre(@Valid @RequestBody GenreRequest genreRequest) {
        log.info("Adding new genre: {}", genreRequest.getName());
        return genreService.addGenre(genreRequest);
    }

    @PutMapping("/{id}")
    public GenreResponse updateGenre(@PathVariable Long id, @Valid @RequestBody GenreRequest genreRequest) {
        log.info("Updating genre with ID: {}", id);
        return genreService.updateGenre(id, genreRequest);
    }

    @GetMapping
    public List<GenreResponse> getAllGenres() {
        log.info("Fetching all genres");
        return genreService.getAllGenres();
    }

    @GetMapping("/{name}")
    public GenreResponse getGenreByName(@PathVariable String name) {
        log.info("Fetching genre with name: {}", name);
        return genreService.getGenreByName(name);
    }
}
