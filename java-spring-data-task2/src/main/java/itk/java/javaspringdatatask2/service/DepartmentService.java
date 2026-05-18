package itk.java.javaspringdatatask2.service;


import itk.java.javaspringdatatask2.entity.Department;

import itk.java.javaspringdatatask2.exception.ResourceNotFoundException;
import itk.java.javaspringdatatask2.repository.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public Department findDepartmentById(Long departmentId){
        return departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    public List<Department> findAllDepartments(){
        return departmentRepo.findAll();
    }
    public Department createOrUpdateDepartment(Department department){

        return departmentRepo.save(department);
    }

    public void deleteDepartment(Long departmentId){
        if (departmentId == null) throw new ResourceNotFoundException("Department not found");
        departmentRepo.deleteById(departmentId);
    }

}
