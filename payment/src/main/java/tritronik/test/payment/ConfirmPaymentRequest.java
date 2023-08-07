package tritronik.test.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmPaymentRequest {
    private Long paymentId;
    private Long reservationId;
    private Long paymentVerifierId;
}
