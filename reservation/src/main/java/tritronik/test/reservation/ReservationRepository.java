package tritronik.test.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Custom methods for reservation repository, if needed

    List<Reservation> findByUser(Long user);
}