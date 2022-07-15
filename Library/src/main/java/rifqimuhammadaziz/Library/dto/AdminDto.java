package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    @Size(min = 3, max = 20, message = "Invalid First Name! (3-20 Characters)")
    private String firstName;

    @Size(min = 3, max = 20, message = "Invalid Last Name! (3-20 Characters)")
    private String lastName;

    private String username;

    @Size(min = 5, max = 20, message = "Invalid Password! (3-20 Characters)")
    private String password;
    private String repeatPassword;
}
