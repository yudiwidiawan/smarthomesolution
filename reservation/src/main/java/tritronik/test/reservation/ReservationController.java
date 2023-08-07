package tritronik.test.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    private final ExternalApiCaller externalAPICaller;

    public ReservationController(ExternalApiCaller externalAPICaller) {
        this.externalAPICaller = externalAPICaller;
    }

//    @Autowired
//    PaymentService paymentService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("")
    public ResponseEntity<Reservation> addNewReservation(@RequestBody ReservationRequest reservationRequest) throws Exception {
        return ResponseEntity.ok(reservationService.addReservation(reservationRequest));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Object> getReservation(@PathVariable("reservationId") Long reservationId) {
        Optional<Reservation> reservation = reservationService.getReservation(reservationId);
        Reservation resvObj = reservation.get();
        return ResponseEntity.ok(resvObj);
    }

    @PostMapping("/check-in")
    public ResponseEntity<Object> checkIn(@RequestBody CheckInRequest checkInRequest) {
        Reservation reservation = reservationService.getReservation(checkInRequest.getReservationId()).get();
        Payment payment = externalAPICaller.callPaymentApi(reservation.getPaymentId());
        if (payment.getStatus().equals("CONFIRMED")) {
            Reservation newReservation =  reservationService.updateCheckIn(checkInRequest.getReservationId(), new Timestamp(System.currentTimeMillis()), "CHECKED_IN");

            return ResponseEntity.ok(Map.of("message", "Check-In success!"));
        }
//        if (reservation.getPayment().getStatus().equals("CONFIRMED") && reservation.getPayment() != null && reservation.getUser() == user) {
//
//        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Check-In failed!"));

    }



}