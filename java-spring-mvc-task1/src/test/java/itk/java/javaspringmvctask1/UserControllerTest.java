package itk.java.javaspringmvctask1;

import itk.java.javaspringmvctask1.controller.UserController;
import itk.java.javaspringmvctask1.entity.Order;
import itk.java.javaspringmvctask1.entity.User;
import itk.java.javaspringmvctask1.exception.ResourceNotFoundException;
import itk.java.javaspringmvctask1.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    private User user;
    private Order order;

    @BeforeEach
    void init(){

        order.setGoods(List.of("GoodOne", "GoodTwo"));
        order.setSumm(1000);
        order.setStatus("Active");

        user.setUserId(1L);
        user.setPhoneNumber("+79123749275");
        user.setName("John");
        user.setOrders(List.of(order));
    }

    @Test
    void shouldReturnSummaryViewForList() throws Exception{

        Page<User> page = new PageImpl<>(List.of(user));
        when(userService.findAllUsers(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/users")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("John"))

                .andExpect(jsonPath("$.content[0].email").doesNotExist())
                .andExpect(jsonPath("$.content[0].phoneNumber").doesNotExist())
                .andExpect(jsonPath("$.content[0].orders").doesNotExist());

    }

    @Test
    void shouldReturnDetailsViewForUser() throws Exception{

        when(userService.findUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.phoneNumber").exists())
                .andExpect(jsonPath("$.orders").exists());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionIfInvalidId() {

        when(userService.findUserById(100L)).thenThrow(ResourceNotFoundException.class);
    }


    @Test
    void shouldReturnBadRequestIfWhenInvalidJson() throws Exception{
        String invalidUserJson = "{\"name\": \"\", \"email\": \"invalid-email\"}";
        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidUserJson))
                .andExpect(status().isBadRequest());

    }
}
