package br.com.dzvolve.minhasfinancas.services.impl;

import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.exceptions.BusinessRuleException;
import br.com.dzvolve.minhasfinancas.repositories.UserRepository;
import br.com.dzvolve.minhasfinancas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository repository){
        super();
        this.userRepository = repository;
    }

    @Override
    public User authenticate(String email, String password) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public boolean validateEmail(String email) {
       boolean exists = userRepository.existsByEmail(email);
       if(exists){
           throw new BusinessRuleException("There is already a user registered with this email.");
       }
    }
}
