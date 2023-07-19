package br.com.dzvolve.minhasfinancas.services;

import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.exceptions.BusinessRuleException;
import br.com.dzvolve.minhasfinancas.repositories.UserRepository;
import br.com.dzvolve.minhasfinancas.services.impl.UserServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before("")
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    public static User createUser(){
        User user = new User(Long.valueOf(1), "willian", "willian@gmail.com", "123", LocalDate.now());
        return user;
    }

    @Test
    public void shouldAuthenticateUserWithSuccess() {
        User userMock = createUser();
        Mockito.when(userRepository.findByEmail(userMock.getEmail())).thenReturn(Optional.of(userMock));

        userService.authenticate(userMock.getEmail(), userMock.getPassword());
    }

    @Test
    public void shouldThrowErrorWhenNotFindUserRegisteredWhitEmailInformed() {
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        userService.authenticate("email@email.com", "senha");
    }

    @Test
    public void shouldThrowErrorWhenPasswordIsDifferent() {
        User user = createUser();
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

        userService.authenticate(user.getEmail(), user.getPassword());
    }

    @Test
    public void shouldValidateEmail(){
        //cenario
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        //ação
        userService.validateEmail("willian@gmail.com");
    }

    @Test
    public void shouldReturnErrorAtValidateEmailWhenExistsEmailRegister(){
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

         userService.validateEmail("willian@gmail.com");

    }
}
