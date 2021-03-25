package rs.ac.singidunum.org.apiusers.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdministratorResponseDto {

    private Long id;
    private String username;
    private String email;
    private boolean isDeleted;

    public AdministratorResponseDto(Long id, String username, String email, boolean deleted) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isDeleted = deleted;
    }
}
