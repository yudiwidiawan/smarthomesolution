package tritronik.test.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user;

    private Long room;

    private String status;

    private Date startDate;

    private Date endDate;

    private Timestamp checkInTime;

    private Timestamp checkoutTime;

    private Long paymentId;


}