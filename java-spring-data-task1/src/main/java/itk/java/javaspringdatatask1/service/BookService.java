package itk.java.javaspringdatatask1.service;

import itk.java.javaspringdatatask1.entity.Book;
import itk.java.javaspringdatatask1.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepo bookRepo;

    public void saveBook(Book book){
        bookRepo.save(book);
    }

    public void updateBook(Book book){
        bookRepo.update(book);

    }
    public Book findBookById(Long bookId){
       return bookRepo.findById(bookId);
    }

    public void deleteBook(Long bookId){
        bookRepo.deleteById(bookId);
    }


}
