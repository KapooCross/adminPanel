package by.itstep.adminPanel.controller;

import by.itstep.adminPanel.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mokMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void whenShowMenu_thenReturnIndexPage() throws Exception{
        mokMvc.perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    void addUser() throws Exception {
        mokMvc.perform(get("/add_user"))
                .andExpect(view().name("add_user"))
                .andExpect(status().is2xxSuccessful()); //status.isOk()
    }

}