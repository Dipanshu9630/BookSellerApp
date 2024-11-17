package dp.practice.project.Book_App_Project.Controllers;

import dp.practice.project.Book_App_Project.Services.UserServices;
import dp.practice.project.Book_App_Project.dto.UserResponseDto;
import dp.practice.project.Book_App_Project.dto.UsersDto;
import dp.practice.project.Book_App_Project.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserServices userServices;
//    @Autowired
//    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UsersDto users){
        log.info("Request received for creating user: {}",users.getEmail());
        userServices.registerNewUser(users);
//        emailService.sendEmail(users.getEmail(),"Registration Confirmation","Congratulation you have been successfully registered on book sellers!!");

        return ResponseEntity.ok("User Registered Successfully!!,  A confirmation email has been sent to your email address.");

    }

   @PutMapping("/update/{id}")
    public  ResponseEntity<String> updateUserDetails(@PathVariable Long id,@RequestBody UsersDto users){

       log.info("Received request to update user with ID: {}", id);
       userServices.updateUserDetails(id,users);

        return ResponseEntity.ok("User updated successfully");
    }
    @GetMapping("/allUsers")
    public List<UserResponseDto> getUsers(){
        log.info("Received request to fetch all users");
        return userServices.getUserDetails();
    }
@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        log.info("Delete request for ID: {} recieved", id);
        userServices.delete(id);
        return ResponseEntity.ok("Record Deleted Successfully");
    }
}
