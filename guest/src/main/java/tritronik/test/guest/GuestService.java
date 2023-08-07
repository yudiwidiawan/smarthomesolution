package tritronik.test.guest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;


    public List<Guest> GetAllGuests() {
        return guestRepository.findAll();
    }

    public Guest AddGuest(GuestRequest guest) {
        Guest newGuest = new Guest();
        newGuest.setFirst_name(guest.getFirst_name());
        newGuest.setLast_name(guest.getLast_name());
        newGuest.setEmail(guest.getEmail());
        newGuest.setGender(guest.getGender());
        newGuest.setAddress(guest.getAddress());
        newGuest.setBirthDate(guest.getBirthDate());
        return guestRepository.save(newGuest);
    }
}
