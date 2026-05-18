package itk.java.javaspringdatatask2.repository;

import itk.java.javaspringdatatask2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
