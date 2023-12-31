package tritronik.test.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
    private Long reservationId;

    private String type;

    private String transferProof;

    private Long paymentAccountId;

}
