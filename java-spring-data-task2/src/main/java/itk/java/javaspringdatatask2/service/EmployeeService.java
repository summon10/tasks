package itk.java.javaspringdatatask2.service;

import itk.java.javaspringdatatask2.entity.Employee;
import itk.java.javaspringdatatask2.exception.ResourceNotFoundException;
import itk.java.javaspringdatatask2.repository.EmployeeProjection;
import itk.java.javaspringdatatask2.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeProjection getEmployeeInfoById(Long employeeId){

        return employeeRepo.getEmployeeInfoByEmployeeId(employeeId);
    }

    public Employee findEmployeeById(Long employeeId){
        return employeeRepo.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public Employee createOrUpdateEmployee(Employee employee){

           return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long employeeId){
        if (employeeId == null) throw new ResourceNotFoundException("Employee not found");
        employeeRepo.deleteById(employeeId);
    }



}
