package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> paymentCollection = new ArrayList<>();

    public Payment save(Payment payment){
        //check the paymentData
        if (payment.getMethod().equals("VOUCHER")){
            String status = checkVoucherId(payment.getPaymentData().get("voucherId"));
            payment.setStatus(status);

        } else if (payment.getMethod().equals("CASH")) {
            String status = checkCashData(payment.getPaymentData());
            payment.setStatus(status);

        }

        paymentCollection.add(payment);
        return payment;
    }
    public Payment findById(String id){
        for (Payment savedPayment: paymentCollection){
            if (savedPayment.getId().equals(id)){
                return savedPayment;
            }
        }
        return null;
    }
    public List<Payment> findAll(){
        return new ArrayList<>(paymentCollection);
    }

    private String checkVoucherId(String voucherId){
        if (voucherId.matches("^ESHOP(?:\\d+[A-Za-z]+|[A-Za-z]+\\d+)[A-Za-z0-9]*$")  && voucherId.length() == 16){
            return PaymentStatus.ACCEPTED.getValue();
        } else {
            return PaymentStatus.REJECTED.getValue();
        }
    }

    private String checkCashData(Map<String, String> paymentData){
        if(paymentData.containsValue(null) || paymentData.containsValue("")){
            return PaymentStatus.REJECTED.getValue();
        }else{
            return PaymentStatus.ACCEPTED.getValue();
        }
    }
}
