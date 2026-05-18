package itk.java.javaspringdatatask2.controller;

import itk.java.javaspringdatatask2.entity.Department;
import itk.java.javaspringdatatask2.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("departments/getDepartments")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.findAllDepartments());

    }
    @PatchMapping("/departments/{departmentId}")
    public ResponseEntity <Department> updateDepartment(
            @Valid @RequestBody Department department
    ){

        return ResponseEntity.ok(departmentService.createOrUpdateDepartment(department));
    }

    @DeleteMapping("/departments/{departmentId}")
    public ResponseEntity <Void> deleteDepartment(
            @PathVariable Long departmentId
    ){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }


}
