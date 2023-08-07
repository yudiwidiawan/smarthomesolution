package tritronik.test.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {
    private final ExternalApiCaller externalAPICaller;

    @Autowired
    PaymentService paymentService;

    @Autowired
    public PaymentController(ExternalApiCaller externalAPICaller) {
        this.externalAPICaller = externalAPICaller;
    }

    @GetMapping("/accounts")
    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentService.getAllPaymentAccounts();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addAccount(@RequestBody PaymentAccountRequest paymentAccountRequest) throws Exception {
        if (paymentService.addPaymentAccount(paymentAccountRequest) != null) {
            return ResponseEntity.ok(Map.of("message" , "Payment account added!"));
        }
        return new ResponseEntity<>(Map.of("message", "Failed to add payment account!"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    public List<Payment> getAllPayments() throws Exception {
       return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public Optional<Payment> getOnePayment(@PathVariable("paymentId") Long paymentId) throws Exception {
        return paymentService.findPaymentById(paymentId);
    }

    @PostMapping("/pay/{reservationId}")
    public ResponseEntity<Object> payReservationApi(@PathVariable("reservationId") Long reservationId, PaymentRequest paymentRequest) throws JsonProcessingException {
        Reservation reservation = externalAPICaller.callApi(reservationId);
        if (reservation.getPaymentId() == null) {
            Float roomPrice = externalAPICaller.callRoomPriceApi(reservation.getRoom());
            Payment payment = paymentService.pay(reservation, paymentRequest, roomPrice);
            return ResponseEntity.ok(Map.of("message" , "Payment sent!", "payment", "http://localhost:8884/v1/payments/" + payment.getId()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "The reservation already paid"));
    }

    @PostMapping("/confirm")
    public ResponseEntity<Object> confirmPayment(@RequestBody ConfirmPaymentRequest cpr) throws Exception {
        Payment payment = paymentService.confirm(cpr);
        return ResponseEntity.ok(Map.of("message" , "Payment confirmed!", "data", payment));
    }

}
