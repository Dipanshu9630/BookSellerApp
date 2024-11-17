package dp.practice.project.Book_App_Project.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class UserResponseDto {
    long id;

    private String name;

    private String email;
    private String phone;
    private String password;
}
