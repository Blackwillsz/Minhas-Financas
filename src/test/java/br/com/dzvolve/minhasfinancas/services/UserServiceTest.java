package br.com.dzvolve.minhasfinancas.services;

import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.repositories.UserRepository;
import br.com.dzvolve.minhasfinancas.services.impl.UserServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before("")
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
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
        User user = new User("willian", "willian@gmail.com");
        userRepository.save(user);

        boolean result = userService.validateEmail("willian@gmail.com");

        Assertions.assertFalse(result);
    }
}
