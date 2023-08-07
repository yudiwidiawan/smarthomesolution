package tritronik.test.payment.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ConfirmPaymentMessage {
    private Long reservationId;
    private String status;

    public ConfirmPaymentMessage(Long reservationId, String status) {
        this.reservationId = reservationId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConfirmPaymentMessage{" +
                "reservationId=" + reservationId +
                ", status='" + status + '\'' +
                '}';
    }
}
