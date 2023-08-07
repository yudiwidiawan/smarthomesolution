package tritronik.test.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApiCaller {
    private final RestTemplate restTemplate;

    private final RestTemplate restTemplateRoom;

    @Autowired
    public ExternalApiCaller(RestTemplate restTemplate, RestTemplate restTemplateRoom) {
        this.restTemplate = restTemplate;
        this.restTemplateRoom = restTemplateRoom;
    }

    public Reservation callApi(Long reservationId) {
        return restTemplate.getForObject("/v1/reservations/" + reservationId, Reservation.class);
    }

    public Float callRoomPriceApi(Long roomId) {
        return restTemplateRoom.getForObject("/v1/rooms/" + roomId + "/price", Float.class);
    }
}
