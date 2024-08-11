package com.app.schedulingsystem.repositories;

import com.app.schedulingsystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    long countByStatus(String status);
    Appointment findByAppointmentId(String appointmentId);
}
