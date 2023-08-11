package tritronik.test.guest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @GetMapping("/v1/guests")
    public List<Guest> GetGuests() {
        return guestService.GetAllGuests();
    }

    @PostMapping("/v1/guests")
    public ResponseEntity<Guest> addNewGuest(@RequestBody GuestRequest guest) throws Exception {
        return ResponseEntity.ok(guestService.AddGuest(guest));
    }


}
