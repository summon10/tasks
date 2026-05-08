package itk.java.javaspringmvctask1.repository;

import itk.java.javaspringmvctask1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository <Order, Long> {
}
