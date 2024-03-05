package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {


   //testCreatePaymentSuccess
    @Test
    void testCreatePaymentDefaultStatus() {

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherId", "ESHOPABC12345678");
        Payment payment = new Payment("123456789", "VOUCHER", paymentData);

        assertTrue(payment.getPaymentData().containsKey("voucherId"));
        assertEquals("CHECKING_PAYMENT", payment.getStatus());
        assertEquals("123456789", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("ESHOPABC12345678", payment.getPaymentData().get("voucher"));

    }
    @Test
    void testCreatePaymentCashDefaultStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Cipete");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("123456789", "CASH", paymentData);

        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));
        assertEquals("CHECKING_PAYMENT", payment.getStatus());
        assertEquals("123456789", payment.getId());
        assertEquals("CASH", payment.getMethod());
        assertEquals("Cipete", payment.getPaymentData().get("address"));
        assertEquals("10000", payment.getPaymentData().get("deliveryFee"));

    }

    // testSetStatusToAccept
    @Test
    void testSetStatusToReject(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherId", "ESHOPABC12345678");
        Payment payment = new Payment("123456789", "VOUCHER", paymentData);

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherId", "ESHOPABC12345678");

        assertThrows(IllegalArgumentException.class, ()-> {
            Payment payment = new Payment("123456789", "pweaseee", paymentData);
        });
    }
    @Test
    void testSetStatusToInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherId", "ESHOPABC12345678");
        Payment payment = new Payment("123456789", "VOUCHER", paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("pweaseee"));

    }
    //testSetStatusToInvalidStatus


}
