package com.apigateway.Repository;

import com.apigateway.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> fetchUserByEmail( @Param("email") String email );

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> fetchUserByUsername( @Param("username") String username );

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.refreshToken = :refreshToken WHERE u.username = :username")
    void updateRefreshToken( @Param("username") String username, @Param("refreshToken") String refreshToken);

}
