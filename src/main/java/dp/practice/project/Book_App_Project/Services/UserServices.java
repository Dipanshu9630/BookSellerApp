package dp.practice.project.Book_App_Project.Services;

import dp.practice.project.Book_App_Project.dto.UserResponseDto;
import dp.practice.project.Book_App_Project.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface UserServices {
    public void registerNewUser(UsersDto users);
    void updateUserDetails(Long id ,UsersDto users);

    public List<UserResponseDto> getUserDetails();
  //  public boolean isUserRegistered(String email);

    public void delete(Long id);

}
