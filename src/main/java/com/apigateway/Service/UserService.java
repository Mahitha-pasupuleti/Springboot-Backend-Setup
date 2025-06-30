package com.apigateway.Service;

import com.apigateway.Entity.User;
import com.apigateway.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {

    public User regsisterUser(User user);
    public User findUserById(Long id);
    public boolean checkIfEmailExists(String email);
    public boolean checkIfUsernameExists(String username);
    public User findUserByUsername(String username);
    public User updateRefreshToken(String username, String refreshToken);

}
