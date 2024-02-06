package models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment  extends BaseModel{

    private int amout;
    private String refNo;

    private PaymentProvider paymentProvider;

    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;

}
