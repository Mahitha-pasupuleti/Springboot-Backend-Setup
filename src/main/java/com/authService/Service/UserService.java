package com.authService.Service;

import com.authService.Entity.User;

public interface UserService {

    public User regsisterUser(User user);
    public User findUserById(Long id);
    public boolean checkIfEmailExists(String email);
    public boolean checkIfUsernameExists(String username);
    public User findUserByUsername(String username);
    public User updateRefreshToken(String username, String refreshToken);

}
