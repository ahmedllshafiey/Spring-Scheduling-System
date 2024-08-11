package com.app.schedulingsystem.controllers;

import java.util.List;

import com.app.schedulingsystem.entities.Admin;
import com.app.schedulingsystem.entities.Appointment;
import com.app.schedulingsystem.entities.User;
import com.app.schedulingsystem.repositories.AppointmentRepo;
import com.app.schedulingsystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @Autowired
    private AppointmentRepo repo;

    @GetMapping("/")
    public String getIndexPage(Model model) {
        return "index";
    }

    @GetMapping("/appointment")
    public String getAppointmentPage(Model model) {
        return "appointment";
    }

    @GetMapping("/dashboard")
    public String getDashboardPage(Model model) {
        List<Appointment> appointments = service.findAllAppointments();
        model.addAttribute("appointments", appointments);
        model.addAttribute("totalAppointments", repo.count());
        model.addAttribute("totalBooked", repo.countByStatus("booked"));
        model.addAttribute("totalPending", repo.countByStatus("pending"));
        return "dashboard";
    }

    @GetMapping("/user/create")
    public String getUserCreate(Model model, @ModelAttribute User user) {
        return "registration";
    }

    @PostMapping("/user/create")
    public String getPostUser(Model model, @ModelAttribute User user) {
        if (service.findByUserEmail(user.getEmail())) {
            model.addAttribute("exist", "This " + user.getEmail() + " Already Exists");
        } else if (service.createUserAccount(user)) {
            model.addAttribute("success", "Your User Account was created successfully");
        } else {
            model.addAttribute("error", "User Account creation failed");
        }
        return "registration";
    }

    @GetMapping("/user/login")
    public String getUserLogin() {
        return "user";
    }

    @PostMapping("/user/login")
    public String postUserLogin(Model model, @ModelAttribute User user) {
        if (service.userLogin(user.getEmail(), user.getPassword())) {
            return "redirect:/appointment";
        } else {
            model.addAttribute("error", "Login Failed");
            return "user";
        }
    }

    @GetMapping("/admin/login")
    public String getAdminLogin(Model model) {
        return "admin";
    }

    @PostMapping("/admin/login")
    public String postAdminLogin(Model model, @ModelAttribute Admin admin) {
        if (service.adminLogin(admin.getEmail(), admin.getPassword())) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Login Failed");
            return "admin";
        }
    }

    @GetMapping("/search")
    public String getStatusPage(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "search";
    }

    @PostMapping("/search")
    public String searchAppointment(@RequestParam("appointmentId") String appointmentId, Model model) {
        Appointment appointmentResult = repo.findByAppointmentId(appointmentId);

        if (appointmentResult != null) {
            model.addAttribute("appointment", appointmentResult);
        } else {
            model.addAttribute("error", "Appointment not found");
        }
        return "search";
    }

    @PostMapping("/appointment")
    public String postIndexPage(Model model, @ModelAttribute Appointment appointment) {
        if (service.saveData(appointment)) {
            model.addAttribute("success", "Your Appointment Request ID: " + appointment.getAppointmentId());
        } else {
            model.addAttribute("error", "Unable to book your appointment due to an error");
        }
        return "appointment";
    }
}
