package itk.java.javaspringdatatask1.repository;

import itk.java.javaspringdatatask1.dao.BookMapper;
import itk.java.javaspringdatatask1.entity.Book;
import itk.java.javaspringdatatask1.exception.ResourceNotFoundException;
import org.apache.catalina.User;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepoImpl implements BookRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;
    public BookRepoImpl(NamedParameterJdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }


    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books (bookId, title, author, year) VALUES (:id, :title, :author, :year)";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(book));
    }

    @Override
    public Book findById(Long bookId) {
        String sql = "SELECT * FROM books WHERE id=:bookId";
        List<Book> books = jdbcTemplate.query(sql, Map.of("bookId", bookId), bookMapper);
        return books.stream().findAny()
                .orElseThrow(()-> new ResourceNotFoundException("Book does not exists"));

    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET title=:title, author=:author, year=:year WHERE bookId=:bookId";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(book));

    }

    @Override
    public void deleteById(Long bookId) {
        String sql = "DELETE FROM books WHERE bookId = :bookId";
        jdbcTemplate.update(sql, Map.of("bookId",bookId));
    }
}
