package tritronik.test.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tritronik.test.room.query.SearchRequest;
import tritronik.test.room.query.SearchSpecification;
import tritronik.test.room.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Page<Room> searchRoom(SearchRequest request) {
        SearchSpecification<Room> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return roomRepository.findAll(specification, pageable);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getWithFilter() {
        return roomRepository.findAll();
    }

    public Room addRoom(RoomRequest room) {
        Room newRoom = new Room();
        newRoom.setType(room.getType());
        newRoom.setPrice(room.getPrice());
        newRoom.setFloorLevel(room.getFloorLevel());
        newRoom.setRoomNumber(room.getRoomNumber());
        newRoom.setStatus(room.getStatus());
        return roomRepository.save(newRoom);
    }


    public Float getRoomPrice(Long roomId) {
        return roomRepository.findById(roomId).get().getPrice();
    }

    public Room updateStatus(Long id, String status) {
        Optional<Room> room = roomRepository.findById(id);
        Room foundRoom = room.get();
        foundRoom.setStatus(status);
        return roomRepository.save(foundRoom);
    }
}