package tritronik.test.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tritronik.test.payment.messages.PaymentMessage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    @Autowired
    private KafkaTemplate<String, PaymentMessage> kafkaTemplate;

    private static final String TOPIC_PAYMENT = "payments";
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentAccountRepository paymentAccountRepository;

    public Optional<Payment> findPaymentById(Long id){
        return paymentRepository.findById(id);
    }

    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountRepository.findAll();
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public PaymentAccount addPaymentAccount(PaymentAccountRequest paymentAccountRequest) {
        PaymentAccount newPaymentAccount = new PaymentAccount();
        newPaymentAccount.setName(paymentAccountRequest.getName());
        newPaymentAccount.setAccountNumber(paymentAccountRequest.getAccountNumber());
        return paymentAccountRepository.save(newPaymentAccount);
    }

    public Payment confirm(ConfirmPaymentRequest cpr) {
        Payment payment = paymentRepository.findById(cpr.getPaymentId()).get();
        payment.setStatus("CONFIRMED");
        payment.setVerifier(cpr.getPaymentVerifierId());
        kafkaTemplate.send(TOPIC_PAYMENT, new PaymentMessage(
                cpr.getReservationId(),
                cpr.getPaymentId(), "CONFIRMED"
        ));
        return paymentRepository.save(payment);
    }

    public Payment pay(Reservation reservation, PaymentRequest paymentRequest, Float roomPrice) {
        if (reservation.getStatus().equals("BOOKED") && reservation.getPaymentId() == null) {
            Payment payment = new Payment();
            payment.setType(paymentRequest.getType());
            payment.setTransferProof(paymentRequest.getTransferProof());
            long diffInMillies = Math.abs(reservation.getStartDate().getTime() - reservation.getEndDate().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            payment.setTotalPayment(diff * roomPrice);
            payment.setStatus("ON_CHECK");
            Payment savedPayment = paymentRepository.save(payment);
            kafkaTemplate.send(TOPIC_PAYMENT, new PaymentMessage(
                    reservation.getId(),
                    savedPayment.getId(), savedPayment.getStatus()
            ));
            return savedPayment;
        }
        return null;
    }
}
