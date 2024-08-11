package com.app.schedulingsystem.repositories;

import com.app.schedulingsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}