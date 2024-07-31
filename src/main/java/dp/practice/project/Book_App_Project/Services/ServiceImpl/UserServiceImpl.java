package dp.practice.project.Book_App_Project.Services.ServiceImpl;

import dp.practice.project.Book_App_Project.Services.UserServices;
import dp.practice.project.Book_App_Project.dto.UserRegistrationDto;
import dp.practice.project.Book_App_Project.entities.Users;
import dp.practice.project.Book_App_Project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public void registerNewUser(UserRegistrationDto userDetails) {
        if (isUserRegistered(userDetails.getEmail())) {
            throw new IllegalArgumentException("User is already registered with this email.");
        }
        Users user = new Users();
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        userRepository.save(user);

    }

    @Override
    public void updateUserDetails(Users users) {
        userRepository.save(users);

    }

    @Override
    public List<Users> getUserDetails() {
        return userRepository.findAll();
    }

    public boolean isUserRegistered(String email){
       return  userRepository.findByEmail(email).isPresent();


    }
}
