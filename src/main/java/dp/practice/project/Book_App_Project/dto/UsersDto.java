package dp.practice.project.Book_App_Project.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersDto {

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty
    private String phone;

    @NotEmpty(message = "Password is required")
    private String password;


}
