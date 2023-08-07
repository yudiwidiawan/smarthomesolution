package tritronik.test.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tritronik.test.room.query.SearchRequest;

import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/search")
    public Page<Room> search(@RequestBody SearchRequest request) {
        return roomService.searchRoom(request);
    }

    @GetMapping
    @ResponseBody
    public List<Room> getAllRooms() {
            
        return roomService.getAllRooms();
    }

    @PostMapping("")
    public ResponseEntity<Room> addNewRoom(@RequestBody RoomRequest room) throws Exception {
        return ResponseEntity.ok(roomService.addRoom(room));
    }

    @GetMapping("/{roomId}/price")
    public Float getRoomPrice(@PathVariable("roomId") Long roomId) {
        return roomService.getRoomPrice(roomId);
    }



    // Other room-related endpoints
}