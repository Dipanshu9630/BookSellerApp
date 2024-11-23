package dp.practice.project.Book_App_Project.Services;

import dp.practice.project.Book_App_Project.dto.GenreRequest;
import dp.practice.project.Book_App_Project.dto.GenreResponse;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface GenreService {
    public GenreResponse addGenre(GenreRequest genreRequest);
    public GenreResponse updateGenre(Long id, GenreRequest genreRequest);
    public GenreResponse getGenreByName(String name);

    public List<GenreResponse> getAllGenres();
}
