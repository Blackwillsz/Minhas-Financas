package br.com.dzvolve.minhasfinancas.services;

import br.com.dzvolve.minhasfinancas.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User authenticate(String email, String password);

    User saveUser(User user);

    void validateEmail(String email);
}
