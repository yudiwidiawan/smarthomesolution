package tritronik.test.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
    private Long roomId;
    private Date startDate;
    private Date endDate;
    private Long userId;
}
