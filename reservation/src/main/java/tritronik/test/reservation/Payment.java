package tritronik.test.reservation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Payment {
    private Long id;

    private String status;

    private String type;

    private String transferProof;

    private Float totalPayment;

    private Long verifier;

}