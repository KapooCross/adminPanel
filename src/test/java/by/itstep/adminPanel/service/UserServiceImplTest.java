package by.itstep.adminPanel.service;

import by.itstep.adminPanel.model.Profession;
import by.itstep.adminPanel.model.Race;
import by.itstep.adminPanel.model.User;
import by.itstep.adminPanel.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void createUser(){
        this.user = User.builder()
                .name("Ivan Tester")
                .race(new Race())
                .profession(new Profession())
                .level(555)
                .dateOfBirth(LocalDate.of(1990,01,01))
                .dateOfRegistration(LocalDate.of(1990,01,01))
                .status("active")
                .build();
    }

    @Test
    void findAllTest() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .name("Ivan Tester2")
                .race(new Race())
                .profession(new Profession())
                .level(3)
                .dateOfBirth(LocalDate.of(1990,01,01))
                .dateOfRegistration(LocalDate.of(2004,03,14))
                .status("active")
                .build()
        );
        users.add(User.builder()
                .name("Ivan Tester3")
                .race(new Race())
                .profession(new Profession())
                .level(5)
                .dateOfBirth(LocalDate.of(2006,11,12))
                .dateOfRegistration(LocalDate.of(2007,01,26))
                .status("active")
                .build()
        );

        when(userRepository.findAll()).thenReturn(users);

        List<User> expected = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(expected.size(), 2);
        assertEquals(expected.get(0).getName(),"Ivan Tester2");
        assertEquals(expected.get(0).getLevel(),3);
        assertEquals(expected.get(1).getName(),"Ivan Tester3");
        assertEquals(expected.get(0).getStatus(),"active");
    }

    @Test
    void whenSave_thenSaveAndReturnUser() {
        when(userRepository.save(any(User.class))).thenReturn(this.user);

        User savedUser = userService.save(this.user);

        assertNotNull(savedUser);
        assertSame(this.user, savedUser);
        verify(userRepository).save(this.user);
    }

    @Test
    void whenExistByIdAndUserExist_thenShouldReturnTrue(){
        when(userRepository.existsById(anyLong())).thenReturn(true);

        boolean actual = userService.existsById(3L);

        assertTrue(actual);
    }

}