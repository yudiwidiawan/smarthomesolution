package tritronik.test.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tritronik.test.payment.messages.PaymentMessage;
import tritronik.test.reservation.messages.CheckInMessage;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private static final String TOPIC = "reservations";
    private static final String TOPIC_CHECKIN = "checkins";

    private static final String TOPIC_CHECKOUT = "checkouts";
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private KafkaTemplate<String, CheckInMessage> kafkaTemplate;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    public Reservation addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setUser(reservationRequest.getUserId());
        reservation.setRoom(reservationRequest.getRoomId());
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setStatus("BOOKED");
//        kafkaTemplate.send(TOPIC, String.format("Booking Kamar %d untuk Tuan/Nyonya %s", room.getId(), user.getLast_name()));
        return reservationRepository.save(reservation);
    }

    public Reservation updatePayment(Long id, Long paymentId, String paymentStatus) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        Reservation reserved = reservation.get();
        reserved.setPaymentId(paymentId);
        reserved.setStatus(paymentStatus);
        return reservationRepository.save(reserved);
    }

    public Reservation updateCheckIn(Long id, Timestamp checkInTime, String status) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        Reservation reserved = reservation.get();
        reserved.setCheckInTime(checkInTime);
        reserved.setStatus(status);
        kafkaTemplate.send(TOPIC_CHECKIN, new CheckInMessage(
                reserved.getRoom(), "UNAVAILABLE"
        ));
        return reservationRepository.save(reserved);
    }

    // Other methods for reservation service
}