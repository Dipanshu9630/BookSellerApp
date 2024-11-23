package dp.practice.project.Book_App_Project.Controllers;

import dp.practice.project.Book_App_Project.Services.BookService;
import dp.practice.project.Book_App_Project.dto.BookRequest;
import dp.practice.project.Book_App_Project.dto.BookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {
//@Autowired
    private final BookService bookService;
//    public static BookService setbook(BookService bookService){
//        this.bookService=bookService;
//        return bookService;
//
//
//    }

    @PostMapping
    public BookResponse addBook(@Valid @RequestBody BookRequest bookRequest) {
        log.info("Adding new book: {}", bookRequest.getTitle());
        return bookService.addBook(bookRequest);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        log.info("Updating book with ID: {}", id);
        return bookService.updateBook(id, bookRequest);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        log.info("Fetching all books");
        return bookService.getAllBooks();
    }

    @GetMapping("/genre/{genreName}")
    public List<BookResponse> getBooksByGenre(@PathVariable String genreName) {
        log.info("Fetching books for genre: {}", genreName);
        return bookService.getBooksByGenre(genreName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        log.info("Deleting book with ID: {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
