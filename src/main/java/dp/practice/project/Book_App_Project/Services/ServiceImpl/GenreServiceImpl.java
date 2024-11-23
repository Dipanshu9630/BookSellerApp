package dp.practice.project.Book_App_Project.Services.ServiceImpl;

import dp.practice.project.Book_App_Project.Services.GenreService;
import dp.practice.project.Book_App_Project.dto.GenreRequest;
import dp.practice.project.Book_App_Project.dto.GenreResponse;
import dp.practice.project.Book_App_Project.entities.Genre;
import dp.practice.project.Book_App_Project.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository=genreRepository;
    }

    /**
     * Add a new genre to the database.
     */
    @Transactional
    public GenreResponse addGenre(GenreRequest genreRequest) {
        log.info("Adding genre: {}", genreRequest.getName());

        // Check if the genre already exists
        genreRepository.findByNameIgnoreCase(genreRequest.getName())
                .ifPresent(existingGenre -> {
                    throw new RuntimeException("Genre with this name already exists");
                });

        // Save the genre
        Genre genre = new Genre();
        genre.setName(genreRequest.getName());
        genre.setDescription(genreRequest.getDescription());

        Genre savedGenre = genreRepository.save(genre);
        log.info("Genre added with ID: {}", savedGenre.getId());

        return mapToResponse(savedGenre);
    }

    /**
     * Update an existing genre.
     */
    @Transactional
    public GenreResponse updateGenre(Long id, GenreRequest genreRequest) {
        log.info("Updating genre with ID: {}", id);

        // Find the genre
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        // Update fields
        genre.setName(genreRequest.getName());
        genre.setDescription(genreRequest.getDescription());

        Genre updatedGenre = genreRepository.save(genre);
        log.info("Genre updated with ID: {}", updatedGenre.getId());

        return mapToResponse(updatedGenre);
    }

    /**
     * Retrieve all genres.
     */
    public List<GenreResponse> getAllGenres() {
        log.info("Fetching all genres");

        return genreRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Find a genre by name.
     */
    public GenreResponse getGenreByName(String name) {
        log.info("Fetching genre with name: {}", name);

        return genreRepository.findByNameIgnoreCase(name)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    private GenreResponse mapToResponse(Genre genre) {
        return new GenreResponse(genre.getId(), genre.getName(), genre.getDescription());
    }
}

