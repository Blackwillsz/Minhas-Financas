package br.com.dzvolve.minhasfinancas.services;

import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.exceptions.BusinessRuleException;
import br.com.dzvolve.minhasfinancas.exceptions.ErrorAuthentication;
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
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {


    @SpyBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @Before("")
    public void setUp() {
        userService = Mockito.spy(UserServiceImpl.class);
    }

    public static User createUser(){
        User user = new User(
                Long.valueOf(1),
                "willian",
                "willian@gmail.com",
                "123",
                LocalDate.now());
        return user;
    }

    @Test
    public void shouldSaveUser() {
//        Mockito.when(userService.validateEmail(Mockito.anyString())).thenReturn(true);
        Mockito.doNothing().when(userService).validateEmail(Mockito.anyString());
        User user = createUser();
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User userSaved = userService.saveUser(new User());

        Assertions.assertNotNull(userSaved);
        Assertions.assertEquals(userSaved.getId(), 1);
        Assertions.assertEquals(userSaved.getName(), "willian");
        Assertions.assertEquals(userSaved.getEmail(), "willian@gmail.com");
        Assertions.assertEquals(userSaved.getPassword(), "123");
    }

    @Test
    public void shouldAuthenticateUserWithSuccess() {
        User userMock = createUser();
        Mockito.when(userRepository.findByEmail(userMock.getEmail())).thenReturn(Optional.of(userMock));

        userService.authenticate(userMock.getEmail(), userMock.getPassword());
    }

    @Test
    public void shouldThrowErrorWhenNotFoundUserRegisteredWhitEmailInformed() {
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        Exception exception = Assertions.assertThrows(BusinessRuleException.class, () -> userService.validateEmail("user@gmail.com"));
        Assertions.assertInstanceOf(BusinessRuleException.class, exception);
    }

    @Test
    public void shouldThrowErrorWhenPasswordIsDifferent() {
        User user = createUser();
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

        Exception exception = Assertions.assertThrows(ErrorAuthentication.class, () -> userService.authenticate(user.getEmail(), "0000"));
        Assertions.assertInstanceOf(ErrorAuthentication.class, exception);

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
        User user = createUser();
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        Exception exception = Assertions.assertThrows(BusinessRuleException.class, () -> userService.validateEmail(user.getEmail()));
        Assertions.assertInstanceOf(BusinessRuleException.class, exception);
    }
}
