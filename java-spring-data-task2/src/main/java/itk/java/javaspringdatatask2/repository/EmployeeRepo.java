package itk.java.javaspringdatatask2.repository;

import itk.java.javaspringdatatask2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

        EmployeeProjection getEmployeeInfoByEmployeeId(Long employeeId);

}
