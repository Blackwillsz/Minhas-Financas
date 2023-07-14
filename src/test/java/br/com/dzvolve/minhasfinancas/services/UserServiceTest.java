package br.com.dzvolve.minhasfinancas.services;

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
       boolean result = userService.validateEmail("user@gmail.com");

        //verificação
        Assertions.assertTrue(result);
    }
}
