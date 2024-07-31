package dp.practice.project.Book_App_Project.Services;

import dp.practice.project.Book_App_Project.dto.UserRegistrationDto;
import dp.practice.project.Book_App_Project.entities.Users;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface UserServices {
    public void registerNewUser(UserRegistrationDto users);
    public void updateUserDetails(Users users);

    public List<Users> getUserDetails();
    public boolean isUserRegistered(String email);

}
