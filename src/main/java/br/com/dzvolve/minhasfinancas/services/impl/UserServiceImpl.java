package br.com.dzvolve.minhasfinancas.services.impl;

import br.com.dzvolve.minhasfinancas.entities.User;
import br.com.dzvolve.minhasfinancas.exceptions.BusinessRuleException;
import br.com.dzvolve.minhasfinancas.exceptions.ErrorAuthentication;
import br.com.dzvolve.minhasfinancas.repositories.UserRepository;
import br.com.dzvolve.minhasfinancas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            throw new ErrorAuthentication("User not found for e-mail.");
        }
        if(!user.get().getPassword().equals(password)){
            throw new ErrorAuthentication("Invalid password.");
        }

        return user.get();
    }

    @Override
    public User saveUser(User user) {
        validateEmail(user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void validateEmail(String email) {
       boolean exists = userRepository.existsByEmail(email);
       if(exists){
           throw new BusinessRuleException("There is already a user registered with this email.");
       }

    }
}
