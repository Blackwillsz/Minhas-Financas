package br.com.dzvolve.minhasfinancas.model.repository;
import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;
    public static User createUser(){
        User user = new User(Long.valueOf(1), "willian", "willian@gmail.com", "wos", LocalDate.now());
        return user;
    }


    @Test
    public void shouldCheckExistenceOfAnEmail(){
        //cenario
        User user = createUser();
       userRepository.save(user);

        //ação/execução
        boolean result = userRepository.existsByEmail("willian@gmail.com");

        //verificação
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhereNotExistsUserRegisterWithEmail(){
        //cenario

        //ação
        boolean result =  userRepository.existsByEmail("willian@gmail.com");

        //verificação
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldPersistUserInDatabase(){
        User user = createUser();

        User userSaved = userRepository.save(user);

        Assertions.assertNotNull(userSaved.getId());
    }

    @Test
    public void shouldSearchUserByEmail(){
        User user = createUser();
        userRepository.save(user);

       Optional<User> result = userRepository.findByEmail("willian@gmail.com");

       Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void shouldReturnVoidWhenNotExistsInDatabase(){
        Optional<User> result = userRepository.findByEmail("willian@gmail.com");

        Assertions.assertFalse(result.isPresent());
    }
}
