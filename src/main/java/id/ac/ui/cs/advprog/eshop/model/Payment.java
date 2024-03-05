package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;


@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData){
        this.id = id;
        this.status = PaymentStatus.CHECKING_PAYMENT.getValue();
        this.paymentData = paymentData;

        if (PaymentMethod.contains(method)){
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public void setStatus(String status){
        if (PaymentStatus.contains(status)){
            this.status = status;
        }else{
            throw new IllegalArgumentException();
        }
    }
}
