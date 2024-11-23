package dp.practice.project.Book_App_Project.Services;

import dp.practice.project.Book_App_Project.dto.BookRequest;
import dp.practice.project.Book_App_Project.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    public BookResponse addBook(BookRequest bookRequest);
    public BookResponse updateBook(Long id, BookRequest bookRequest);
    public List<BookResponse> getAllBooks();
    public List<BookResponse> getBooksByGenre(String genreName);
    public void deleteBook(Long id);
}
