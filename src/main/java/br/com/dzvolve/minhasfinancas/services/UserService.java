package br.com.dzvolve.minhasfinancas.services;

import br.com.dzvolve.minhasfinancas.entities.User;

public interface UserService {

    User authenticate(String email, String password);

    User saveUser(User user);

    boolean validateEmail(String email);
}
