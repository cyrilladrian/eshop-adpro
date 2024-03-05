package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;


@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData){
        this.id = id;
        this.status = "CHECKING_PAYMENT";
        this.setMethod(method);
        this.paymentData = paymentData;

    }

    private void setMethod(String method){
        String[] methodList = {"VOUCHER", "CASH"};
        if (Arrays.stream(methodList).noneMatch(item -> item.equals(method))){
            throw new IllegalArgumentException();
        }else{
            this.method  = method;
        }
    }

    public void setStatus(String status){
        String[] statusList = {"ACCEPTED","REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> item.equals(status))){
            throw new IllegalArgumentException();
        }else{
            this.status = status;
        }
    }
}
