package tritronik.test.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import tritronik.test.reservation.messages.CheckInMessage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RoomApplication {

	public static void main(String[] args) throws Exception{
		ConfigurableApplicationContext context = SpringApplication.run(RoomApplication.class, args);
		MessageListener listener = context.getBean(MessageListener.class);

		listener.latch.await(10, TimeUnit.SECONDS);
	}

	@Bean
	public MessageListener messageListener() {
		return new MessageListener();
	}

	public static class MessageListener {

		@Autowired
		RoomService roomService;

		private CountDownLatch latch = new CountDownLatch(3);
		private CountDownLatch paymentLatch = new CountDownLatch(1);


		@KafkaListener(topics = "${checkins.topic.name}", containerFactory = "checkInsKafkaListenerContainerFactory")
		public void paymentListener(CheckInMessage message) {
			System.out.println("Received check-in message: " + message);
			roomService.updateStatus(message.getRoomId(), message.getStatus());
			this.paymentLatch.countDown();
		}

	}

}
