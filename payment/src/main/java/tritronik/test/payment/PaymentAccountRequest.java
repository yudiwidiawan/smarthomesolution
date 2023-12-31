package tritronik.test.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentAccountRequest {
    private Long accountNumber;
    private String name;
}
