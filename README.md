# Appointment Scheduling System

The Appointment Scheduling System includes operations such as sending your appointment request to the admin. By providing the required details for each appointment request, a random string with the prefix “RQ” is created for tracing your appointment. In this article, we explain the Appointment Scheduling System in Spring Boot. In this web application, you can send appointment requests to the admin by providing user details and other necessary information to confirm the appointment.

This application also provides alert messages based on the appointment status, such as booked or pending, depending on the application’s status.

Application Functionality:
Create User Account: The User can create their account by providing user details like username, email, and password
User Login: The User can log in by using the user credentials email and password.
Book Appointment: The End User can request for appointment by providing the required details.
Admin Login: The Admin can log in using user credentials email and password.
View all appointments as Admin: As admin we can able seen all the requested appointments data in the form of table
Search Appointment: By using Appointment Request Id we can track our application by using search feature
Random Appointment Request ID generation: For every appointment request we created a unique ID for tracing the appointment request. Here we use RD as prefix the suffix is 5 digit number which is random number.
Appointment Categories: Here we provide a feature to show how many appointments are booked and how many are in pending.
Below we provide required information with related examples.

Prerequisites:
To understand this Appointment Scheduling System in Spring Boot, you need basic knowledge of below technologies:

 - Spring Boot
 - Thymeleaf
 - Postgres DB
 - Other Spring MVC Patterns
 - Bootstrap Framework

The Spring Boot is used for creating web applications with MVC pattern, Thymeleaf is used for generating dynamic HTML content in the web pages as well and the main purpose is integrating the HTML pages with Spring framework, and Postgres DB is used for Data Storage.