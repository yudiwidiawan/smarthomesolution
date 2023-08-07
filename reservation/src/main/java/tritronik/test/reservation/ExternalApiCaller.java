package tritronik.test.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApiCaller {
    private final RestTemplate restTemplatePayment;


    @Autowired
    public ExternalApiCaller(RestTemplate restTemplatePayment) {
        this.restTemplatePayment = restTemplatePayment;
    }

    public Payment callPaymentApi(Long paymentId) {
        return restTemplatePayment.getForObject("/v1/payments/" + paymentId, Payment.class);
    }

}
