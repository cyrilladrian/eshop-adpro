package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    List<Payment> paymentList;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();
        paymentList = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherId", "ESHOPABC12345678");
        Payment payment1 = new Payment("123456789","VOUCHER", paymentData1);
        paymentList.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData1.put("address", "Cipete");
        paymentData2.put("deliveryFee", "10000");
        Payment payment2 = new Payment("2234567890","CASH", paymentData2);
        paymentList.add(payment2);

    }

    //testSaveCreate; check for id length -> status accepted
    @Test
    void testSaveCreate(){
        Payment payment = paymentList.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(paymentList.get(1).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertTrue(findResult.getPaymentData().containsKey("voucherId"));
        assertEquals(payment.getPaymentData().get("voucherId"), findResult.getPaymentData().get("voucherId") );
        assertEquals(16, findResult.getPaymentData().get("voucherId").length());
        assertEquals(PaymentStatus.ACCEPTED.getValue(), findResult.getStatus());
    }

    //testSaveVoucherIdInvalid -> status rejected
    @Test
    void testSaveVoucherIdInvalid(){
        Map<String, String> paymentDataInvalid= new HashMap<>();
        paymentDataInvalid.put("voucherId", "pweasee");
        Payment paymentInvalid = new Payment("123456789","VOUCHER", paymentDataInvalid);
        paymentRepository.save(paymentInvalid);

        Payment findResult = paymentRepository.findById(paymentInvalid.getId());
        assertEquals(paymentInvalid.getId(), findResult.getId());
        assertEquals(paymentInvalid.getMethod(), findResult.getMethod());
        assertEquals(paymentInvalid.getPaymentData().get("voucherId"), findResult.getPaymentData().get("voucherId") );
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    //testSaveCashNullInPaymentData -> status rejected
    @Test
    void testSaveCashNullInPaymentData(){
        Map<String, String> paymentDataInvalid= new HashMap<>();
        paymentDataInvalid.put("address", "pweasee");
        paymentDataInvalid.put("deliveryFee", null);
        Payment paymentInvalid = new Payment("123456789","CASH", paymentDataInvalid);
        paymentRepository.save(paymentInvalid);

        Payment findResult = paymentRepository.findById(paymentInvalid.getId());
        assertEquals(paymentInvalid.getId(), findResult.getId());
        assertEquals(paymentInvalid.getMethod(), findResult.getMethod());
        assertTrue(findResult.getPaymentData().containsKey("address"));
        assertTrue(findResult.getPaymentData().containsKey("deliveryFee"));
        assertTrue(findResult.getPaymentData().containsValue(null));
        assertEquals(paymentInvalid.getPaymentData().get("address"), findResult.getPaymentData().get("address") );
        assertEquals(paymentInvalid.getPaymentData().get("deliveryFee"), findResult.getPaymentData().get("deliveryFee") );
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }
    //testSaveCashEmptyInPaymentData -> status rejected
    @Test
    void testSaveCashEmptyInPaymentData(){
        Map<String, String> paymentDataInvalid= new HashMap<>();
        paymentDataInvalid.put("address", "");
        paymentDataInvalid.put("deliveryFee", "10000");
        Payment paymentInvalid = new Payment("123456789","CASH", paymentDataInvalid);
        paymentRepository.save(paymentInvalid);

        Payment findResult = paymentRepository.findById(paymentInvalid.getId());
        assertEquals(paymentInvalid.getId(), findResult.getId());
        assertEquals(paymentInvalid.getMethod(), findResult.getMethod());
        assertTrue(findResult.getPaymentData().containsKey("address"));
        assertTrue(findResult.getPaymentData().containsKey("deliveryFee"));
        assertTrue(findResult.getPaymentData().containsValue(""));
        assertEquals(paymentInvalid.getPaymentData().get("address"), findResult.getPaymentData().get("address") );
        assertEquals(paymentInvalid.getPaymentData().get("deliveryFee"), findResult.getPaymentData().get("deliveryFee") );
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }
    //testFindByIdFound
    @Test
    void testFindByIdIfFound(){
        for (Payment payment: paymentList){
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById(paymentList.get(1).getId());
        assertEquals(paymentList.get(1).getId(), findResult.getId());
        assertEquals(paymentList.get(1).getMethod(), findResult.getMethod());
        assertEquals(paymentList.get(1).getStatus(), findResult.getStatus());
        assertEquals(paymentList.get(1).getPaymentData(), findResult.getPaymentData());
    }
    //testFindByIdNotFound
    @Test
    void testFindByIdIfIdNotFound(){
        for (Payment payment: paymentList){
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("pweaseee");
        assertNull(findResult);
    }
    //testFindAllIfNotEmpty
    @Test
    void testFindAllIfNotEmpty(){
        for (Payment payment: paymentList){
            paymentRepository.save(payment);
        }

        List <Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
    }
    //testFindAllIfEmpty
    @Test
    void testFindAllIfEmpty(){
        List<Payment> payments = paymentRepository.findAll();

        assertTrue(payments.isEmpty());
    }

}
