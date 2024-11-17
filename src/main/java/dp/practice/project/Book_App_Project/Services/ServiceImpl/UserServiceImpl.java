package dp.practice.project.Book_App_Project.Services.ServiceImpl;

import dp.practice.project.Book_App_Project.Exceptions.UserAlreadyExistsException;
import dp.practice.project.Book_App_Project.Services.UserServices;
import dp.practice.project.Book_App_Project.dto.UserResponseDto;
import dp.practice.project.Book_App_Project.dto.UsersDto;
import dp.practice.project.Book_App_Project.entities.Users;
import dp.practice.project.Book_App_Project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public void registerNewUser(UsersDto userDetails) {

        log.info("Creating user with email: {}",userDetails.getEmail());
        userRepository.findByEmail(userDetails.getEmail()).ifPresent(user->{
            log.error("User with {} email already exists",userDetails.getEmail());
            throw  new UserAlreadyExistsException("User with email already exists");
        });
        userRepository.save(
                Users.builder()
                        //.id(userDetails.getId())
                        .name(userDetails.getName())
                        .email(userDetails.getEmail())
                        .password(userDetails.getPassword())
                        .phone(userDetails.getPhone())
                        .build()


        );
        log.info("User created with {} email",userDetails.getEmail());

    }



    @Override
    public void updateUserDetails(Long id, UsersDto users) {

        log.info("updating user with ID: {}",id);
         userRepository.findById(id).orElseThrow(()->{
                    log.error("User with ID: {} not found",id);
                    return new RuntimeException("User not found");
                }
        );
        userRepository.findByEmail(users.getEmail())
                .filter(existingUser -> !Objects.equals(existingUser.getId(), id)) // Check for different user
                .ifPresent(existingUser -> {
                    log.error("Email {} is already taken by another user", users.getEmail());
                    throw new RuntimeException("Email is already in use");
                });


        userRepository.save(
                Users.builder()
                        .id(id)
                        .name(users.getName())
                        .email(users.getEmail())
                        .password(users.getPassword())
                        .phone(users.getPhone())
                        .build()
        );
        log.info("User with ID {} updated successfully", id);


    }

    @Override
    public List<UserResponseDto> getUserDetails() {

        return userRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            log.error("User with ID {} not found", id);
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
        log.info("User with ID {} deleted successfully", id);
    }


    private UserResponseDto mapToResponse(Users user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .build();
    }

}
