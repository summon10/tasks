package itk.java.javaspringdatatask1.repository;


import itk.java.javaspringdatatask1.entity.Book;
import java.util.Optional;

public interface BookRepo {
    void save(Book book);
    Book findById(Long bookId);
    void update(Book book);
    void deleteById(Long id);

}
