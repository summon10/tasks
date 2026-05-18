package itk.java.javaspringdatatask2.controller;


import itk.java.javaspringdatatask2.entity.Employee;
import itk.java.javaspringdatatask2.repository.EmployeeProjection;
import itk.java.javaspringdatatask2.repository.EmployeeRepo;
import itk.java.javaspringdatatask2.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;


    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeProjection> getEmployeeInfo(
            @Valid @PathVariable Long employeeId){

        return ResponseEntity.ok()
                .body(employeeService.getEmployeeInfoById(employeeId));
    }

    @PatchMapping("/employees/{employeeId}")
    public ResponseEntity <Employee> updateEmployee(
            @PathVariable Long employeeId,
            @Valid @RequestBody Employee employee
    ){

        return ResponseEntity.ok(employeeService.createOrUpdateEmployee(employee));
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity <Void> deleteEmployee(
            @PathVariable Long employeeId
    ){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }


}
