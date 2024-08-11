package com.app.schedulingsystem.services;

import java.util.List;
import java.util.Random;

import com.app.schedulingsystem.entities.Admin;
import com.app.schedulingsystem.entities.Appointment;
import com.app.schedulingsystem.entities.User;
import com.app.schedulingsystem.repositories.AdminRepo;
import com.app.schedulingsystem.repositories.AppointmentRepo;
import com.app.schedulingsystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AdminRepo adminRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Creates a new user account.
     * @param user The user object containing user details.
     * @return true if the user account is successfully created, false otherwise.
     */
    public boolean createUserAccount(User user) {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            return false; // Email already exists
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    /**
     * Finds a user by their email.
     * @param email The email of the user to find.
     * @return true if the user is found.
     */
    public boolean findByUserEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }

    /**
     * Authenticates a user based on email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return true if the email and password match a user, false otherwise.
     */
    public boolean userLogin(String email, String password) {
        User user = userRepo.findByEmail(email);
        return user != null && encoder.matches(password, user.getPassword());
    }

    /**
     * Authenticates an admin based on email and password.
     * @param email The email of the admin.
     * @param password The password of the admin.
     * @return true if the email and password match an admin, false otherwise.
     */
    public boolean adminLogin(String email, String password) {
        Admin admin = adminRepo.findByEmailAndPassword(email, password);
        return admin != null && encoder.matches(password, admin.getPassword());
    }

    /**
     * Saves an appointment.
     * @param appointment The appointment object to save.
     * @return true if the appointment is successfully saved.
     */
    public boolean saveData(Appointment appointment) {
        appointment.setAppointmentId(generateRandomString());
        repo.save(appointment);
        return true;
    }

    /**
     * Retrieves all appointments.
     * @return A list of all appointments.
     */
    public List<Appointment> findAllAppointments() {
        return repo.findAll();
    }

    /**
     * Generates a random string for appointment ID.
     *
     * @return A random string prefixed with "RQ" followed by 5 random digits.
     */
    public static String generateRandomString() {
        final String FIXED_PART = "RQ";
        final int RANDOM_PART_LENGTH = 5;
        StringBuilder sb = new StringBuilder(FIXED_PART);
        Random random = new Random();

        for (int i = 0; i < RANDOM_PART_LENGTH; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
