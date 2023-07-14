package br.com.dzvolve.minhasfinancas.model.repository;
import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCheckExistenceOfAnEmail(){
        //cenario
        User user = new User(Long.valueOf(1), "user", "user@gmail.com", "123", LocalDate.now());
        userRepository.save(user);

        //ação/execução
        boolean result = userRepository.existsByEmail("user@gmail.com");

        //verificação
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhereNotExistsUserRegisterWithEmail(){
        //cenario
        userRepository.deleteAll();

        //ação
        boolean result =  userRepository.existsByEmail("user@gmail.com");

        //verificação
        Assertions.assertFalse(result);
    }
}
