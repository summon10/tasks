package itk.java.javaspringdatatask1.controller;

import itk.java.javaspringdatatask1.dto.BookDTO;
import itk.java.javaspringdatatask1.entity.Book;
import itk.java.javaspringdatatask1.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> findBookById(
            @PathVariable @Valid Long bookId
    ){
        return ResponseEntity.ok(bookService.findBookById(bookId));

    }

    @PostMapping("/books/createBook")
    public ResponseEntity<String> createBook(
            @Valid @RequestBody Book book)
    {
        bookService.saveBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("created successfully");
    }

    @PatchMapping("/books/{bookId}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long bookId,
            @Valid @RequestBody BookDTO bookDTO
    ){
       Book existingBook = bookService.findBookById(bookId);

        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setYear(bookDTO.getYear());
        bookService.updateBook(existingBook);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Updated Successfully");
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<String> deleteBook(
            @PathVariable @Valid Long bookId
    ){
        bookService.deleteBook(bookId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Deleted Successfully");
    }



}
