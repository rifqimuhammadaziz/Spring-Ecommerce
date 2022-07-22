package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @Size(min = 3, max = 15, message = "First Name must be 3-15 characters")
    private String firstName;

    @Size(min = 3, max = 30, message = "Last Name must be 3-30 characters")
    private String lastName;

    private String username;

    @Size(min = 5, max = 30, message = "Password must be 5-30 characters")
    private String password;

    private String retypePassword;
}
