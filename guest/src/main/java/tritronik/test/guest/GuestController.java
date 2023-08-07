package tritronik.test.guest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guests")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @GetMapping("")
    public List<Guest> GetGuests() {
        return guestService.GetAllGuests();
    }

    @PostMapping("")
    public ResponseEntity<Guest> addNewGuest(@RequestBody GuestRequest guest) throws Exception {
        return ResponseEntity.ok(guestService.AddGuest(guest));
    }


}
