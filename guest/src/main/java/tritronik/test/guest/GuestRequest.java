package tritronik.test.guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GuestRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private Gender gender;
    private Date birthDate;
}