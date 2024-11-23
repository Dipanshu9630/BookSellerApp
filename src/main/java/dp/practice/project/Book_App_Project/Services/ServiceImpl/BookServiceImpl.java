package dp.practice.project.Book_App_Project.Services.ServiceImpl;

import dp.practice.project.Book_App_Project.Services.BookService;
import dp.practice.project.Book_App_Project.dto.BookRequest;
import dp.practice.project.Book_App_Project.dto.BookResponse;
import dp.practice.project.Book_App_Project.entities.Books;
import dp.practice.project.Book_App_Project.entities.Genre;
import dp.practice.project.Book_App_Project.repositories.BookRepository;
import dp.practice.project.Book_App_Project.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

//  public BookServiceImpl(BookRepository bookRepository){
//      this.bookRepository=bookRepository;
//  }



//  public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository){
//      this.bookRepository = bookRepository;
//      this.genreRepository=genreRepository;
//  }

    /**
     * Add a new book.
     */
    @Transactional
    public BookResponse addBook(BookRequest bookRequest) {
        log.info("Adding book: {}", bookRequest.getTitle());

        // Check if book already exists
        bookRepository.findByTitleIgnoreCase(bookRequest.getTitle())
                .ifPresent(existingBook -> {
                    throw new RuntimeException("Book with this title already exists");
                });

        // Validate genre
        Genre genre = genreRepository.findByNameIgnoreCase(bookRequest.getGenre())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        // Save book
        Books book = new Books();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPrice(bookRequest.getPrice());
        book.setQuantity(bookRequest.getQuantity());
        book.setGenre(genre);

        Books savedBook = bookRepository.save(book);
        log.info("Book added with ID: {}", savedBook.getId());

        return mapToResponse(savedBook);
    }

    /**
     * Update an existing book.
     */
    @Transactional
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        log.info("Updating book with ID: {}", id);

        // Find book
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Validate genre
        Genre genre = genreRepository.findByNameIgnoreCase(bookRequest.getGenre())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        // Update fields
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPrice(bookRequest.getPrice());
        book.setQuantity(bookRequest.getQuantity());
        book.setGenre(genre);

        Books updatedBook = bookRepository.save(book);
        log.info("Book updated with ID: {}", updatedBook.getId());

        return mapToResponse(updatedBook);
    }

    /**
     * Get all books.
     */
    public List<BookResponse> getAllBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get books by genre.
     */
    public List<BookResponse> getBooksByGenre(String genreName) {
        log.info("Fetching books for genre: {}", genreName);
        return bookRepository.findByGenreNameIgnoreCase(genreName)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Delete a book by ID.
     */
    @Transactional
    public void deleteBook(Long id) {
        log.info("Deleting book with ID: {}", id);
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
        log.info("Book with ID {} deleted", id);
    }

    private BookResponse mapToResponse(Books book) {
//        return new BookResponse(
//                        book.getId(),
//                        book.getTitle(),
//                        book.getAuthor(),
//                        book.getPrice(),
//                        book.getQuantity(),
//                        book.getGenre().getName()
//
//                );
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .quantity(book.getQuantity())
                .genre(book.getGenre().getName())
                .build();
    }
}
