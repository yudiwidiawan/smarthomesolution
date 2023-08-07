package tritronik.test.guest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity(name = "Guest")
@Getter
@Table(name = "guest", uniqueConstraints = @UniqueConstraint(name = "guest_email_unique", columnNames = "email"))
public class Guest {
    @Id
    @SequenceGenerator(
            name = "guest_sequence",
            sequenceName = "guest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "guest_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Column(name = "birth_date")
    @JsonProperty("birth_date")
    private Date birthDate;
}
