package tritronik.test.payment.messages;

public class PaymentMessage {

    private Long reservationId;
    private Long paymentId;
    private String status;

    public PaymentMessage(Long reservationId, Long paymentId, String status) {
        this.reservationId = reservationId;
        this.paymentId = paymentId;
        this.status = status;
    }

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
