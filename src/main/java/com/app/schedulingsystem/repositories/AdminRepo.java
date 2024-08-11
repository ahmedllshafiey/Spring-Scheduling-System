package com.app.schedulingsystem.repositories;

import com.app.schedulingsystem.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    Admin findByEmailAndPassword(String email, String password);
}
