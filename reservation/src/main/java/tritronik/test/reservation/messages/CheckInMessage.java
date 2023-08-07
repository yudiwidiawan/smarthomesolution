package tritronik.test.reservation.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CheckInMessage {
    @JsonProperty("roomId")
    private Long roomId;
    @JsonProperty("status")
    private String status;

    @JsonCreator
    public CheckInMessage() {
    }

    public CheckInMessage(Long roomId, String status) {
        this.roomId = roomId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CheckInMessage{" +
                "roomId=" + roomId +
                ", status='" + status + '\'' +
                '}';
    }
}
