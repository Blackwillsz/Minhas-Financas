package br.com.dzvolve.minhasfinancas.services;

import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Test
    public void shouldValidateEmail(){
        //cenario
        userRepository.deleteAll();

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
