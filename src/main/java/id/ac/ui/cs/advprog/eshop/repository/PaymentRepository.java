package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private List<Payment> paymentCollection = new ArrayList<>();

    public Payment save(Payment payment){
        //check the paymentData
        if (payment.getMethod().equals("VOUCHER")){
            if (payment.getPaymentData().get("voucherId").matches("^ESHOP(?:\\d+[A-Za-z]+|[A-Za-z]+\\d+)[A-Za-z0-9]*$")  && payment.getPaymentData().get("voucherId").length() == 16){
                payment.setStatus(PaymentStatus.ACCEPTED.getValue());
            } else {
                payment.setStatus(PaymentStatus.REJECTED.getValue());
            }
        } else if (payment.getMethod().equals("CASH")) {
            if(payment.getPaymentData().containsValue(null) || payment.getPaymentData().containsValue("")){
                payment.setStatus(PaymentStatus.REJECTED.getValue());
            }else{
                payment.setStatus(PaymentStatus.ACCEPTED.getValue());
            }
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
}
