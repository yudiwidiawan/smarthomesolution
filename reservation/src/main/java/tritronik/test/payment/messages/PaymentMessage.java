package tritronik.test.payment.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PaymentMessage.class)
public class PaymentMessage {

    @JsonCreator
    public PaymentMessage() {
    }

    public PaymentMessage(Long reservationId, Long paymentId, String status) {
        super();
        this.reservationId = reservationId;
        this.paymentId = paymentId;
        this.status = status;
    }

    @JsonProperty("reservationId")
    private Long reservationId;

    @JsonProperty("paymentId")
    private Long paymentId;
    @JsonProperty("status")
    private String status;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "PaymentMessage{" +
                "reservationId=" + reservationId +
                ", paymentId=" + paymentId +
                ", status='" + status + '\'' +
                '}';
    }
}
