package tritronik.test.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import tritronik.test.payment.messages.PaymentMessage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(ReservationApplication.class, args);
		MessageListener listener = context.getBean(MessageListener.class);

		listener.latch.await(10, TimeUnit.SECONDS);
	}

	@Bean
	public MessageListener messageListener() {
		return new MessageListener();
	}

	public static class MessageListener {

		@Autowired
		ReservationService reservationService;

		private CountDownLatch latch = new CountDownLatch(3);
		private CountDownLatch paymentLatch = new CountDownLatch(1);


		@KafkaListener(topics = "${payments.topic.name}", containerFactory = "paymentsKafkaListenerContainerFactory")
		public void paymentListener(PaymentMessage message) {
			System.out.println("Received check-in message: " + message);
			reservationService.updatePayment(message.getReservationId(), message.getPaymentId(), message.getStatus());
			this.paymentLatch.countDown();
		}

	}

}
