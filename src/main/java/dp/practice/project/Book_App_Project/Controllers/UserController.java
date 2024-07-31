package dp.practice.project.Book_App_Project.Controllers;

import dp.practice.project.Book_App_Project.Services.EmailService;
import dp.practice.project.Book_App_Project.Services.UserServices;
import dp.practice.project.Book_App_Project.dto.UserRegistrationDto;
import dp.practice.project.Book_App_Project.entities.Users;
import dp.practice.project.Book_App_Project.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserRegistrationDto users){

//        if((userRepository.findByEmail(users.getEmail()).isPresent())){
//           return ResponseEntity.badRequest().body("User is already registered");
//
//        }

        userServices.registerNewUser(users);
        emailService.sendEmail(users.getEmail(),"Registration Confirmation","Congratulation you have been successfully registered on book sellers!!");

        return ResponseEntity.ok("User Registered Successfully!!,  A confirmation email has been sent to your email address.");

    }

   @PutMapping("/update")
    public  ResponseEntity<String> updateUserDetails(@RequestParam String userEmail,@RequestBody Users users){

        return  null;
    }
    @GetMapping("/allUsers")
    public List<Users> getUsers(){
        return userServices.getUserDetails();
    }
}
