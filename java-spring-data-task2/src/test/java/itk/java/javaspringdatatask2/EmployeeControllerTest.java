package itk.java.javaspringdatatask2;


import itk.java.javaspringdatatask2.controller.EmployeeController;
import itk.java.javaspringdatatask2.exception.ResourceNotFoundException;
import itk.java.javaspringdatatask2.repository.EmployeeProjection;
import itk.java.javaspringdatatask2.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmployeeService employeeService;

    @Test
    public void shouldReturnInfo() throws Exception {
        Long employeeId = 1L;
        EmployeeProjection mockEmployeeProjection = Mockito.mock(EmployeeProjection.class);

        given(mockEmployeeProjection.getFullName()).willReturn("John Johnson");
        given(mockEmployeeProjection.getPosition()).willReturn("SA");
        given(mockEmployeeProjection.getDepartmentName()).willReturn("IT");

        given(employeeService.getEmployeeInfoById(employeeId)).willReturn(mockEmployeeProjection);
        mockMvc.perform(get("/employees/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Johnson"))
                .andExpect(jsonPath("$.position").value("SA"))
                .andExpect(jsonPath("$.departmentName").value("IT"));

    }

    @Test
    public void shouldThrowsResourceNotFoundExceptionWhenInvalidId() throws Exception {
        doThrow(new ResourceNotFoundException("Employee not found"))
                .when(employeeService).deleteEmployee(100L);
        mockMvc.perform(delete("/employees/{id}", 100L))
                .andExpect(status().isNotFound());
    }

}
