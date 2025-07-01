package com.authService.Service;

import com.authService.CustomExceptions.EmailAlreadyExistsException;
import com.authService.CustomExceptions.UserAlreadyExistsException;
import com.authService.CustomExceptions.UserNotFoundException;
import com.authService.Entity.User;
import com.authService.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public User regsisterUser(User user) {
            if (checkIfEmailExists(user.getEmail()))
                throw new EmailAlreadyExistsException("Email Already Exists! Please use another email!");
            if (checkIfUsernameExists(user.getUsername()))
                throw new UserAlreadyExistsException("Username already exists! Please use another username!");
            User savedUser = userRepository.save(user);
            return savedUser;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.fetchUserByEmail(email).isPresent();
    }

    public boolean checkIfUsernameExists(String username) {
        return userRepository.fetchUserByUsername(username).isPresent();
    }

    public User findUserByUsername(String username) {
        return userRepository.fetchUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found."));
    }

    @Override
    @Transactional
    public User updateRefreshToken(String username, String refreshToken) {
        User user = findUserByUsername(username);
        user.setRefreshToken( refreshToken );
        return userRepository.save(user);
    }
}
