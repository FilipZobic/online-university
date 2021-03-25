package rs.ac.singidunum.org.apiusers.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdministratorRequestDto {

    private String username;
    private String password;
    private String email;
}
