package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private Payment payment;

    private static boolean hasNullValue(Map<String, String> map) {
        for (String value : map.values()) {
            if (value == null) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasEmptyValue(Map<String, String> map) {
        for (String value : map.values()) {
            if (value.isEmpty()) {
                return true;
            }
        }
        return false;
    }


    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreatePaymentInvalidStatus(){
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("voucherCode", "ESHOPABC12345678");

        this.payment = new Payment("123456789","voucher", subFeature);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("mamakloe"));
    }

    @Test
    void testCreatePaymentInvalidSubFeatureName(){
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("voucherCode", "ESHOPABC12345678");
        assertThrows(IllegalArgumentException.class, ()-> {
            this.payment = new Payment("123456789","mamakloe", subFeature);
        });
    }

    @Test
    void testCreatePaymentReject_InvalidVoucherLength(){
        Map<String, String> subFeature = new HashMap<>();
        String validId ="^ESHOP.*(?=\\d{8})).*$";
        subFeature.put("voucherId", validId);

        this.payment = new Payment("123456789", "voucher", subFeature);

        assertNotEquals(16, payment.getPaymentData().get("voucherId").length());
        assertEquals("REJECT", payment.getStatus());
    }

    @Test
    void testCreatePaymentReject_InvalidVoucherId (){
        Map<String, String> subFeature = new HashMap<>();
        String invalidId = "^(?!ESHOP)(?!\\d{8})$";
        subFeature.put("voucherId", invalidId );

        this.payment = new Payment("123456789", "voucher", subFeature);

        assertEquals("REJECT", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherSuccess (){
        Map<String, String> subFeature = new HashMap<>();
        String validId ="^ESHOP(?=\\d{8})$";
        subFeature.put("voucherId", validId);

        this.payment = new Payment("123456789", "voucher", subFeature);

        assertEquals(16, payment.getPaymentData().get("voucherId").length());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentCashSuccess() {
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("address", "Cipete");
        subFeature.put("deliveryFee", "10000");

        Payment payment = new Payment("123456789", "cash", subFeature);

        assertNotNull(payment.getPaymentData().get("address"));
        assertNotNull(payment.getPaymentData().get("deliveryFee"));
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentCashReject_AllSubFieldsNull() {
        Map<String, String> subFeature = new HashMap<>();

        Payment payment = new Payment("123456789", "cash", subFeature);

        assertNull(payment.getPaymentData().get("address"));
        assertNull(payment.getPaymentData().get("deliveryFee"));
        assertEquals("REJECT", payment.getStatus());
    }

    @Test
    void testCreatePaymentCashReject_AllEmptySubFields() {
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("address", "");
        subFeature.put("deliveryFee", "");

        Payment payment = new Payment("123456789", "cash", subFeature);

        assertTrue(hasEmptyValue(payment.getPaymentData()));
        assertEquals("REJECT", payment.getStatus());
    }
    @Test
    void testCreatePaymentCashReject_ExistSubFieldsNull1() {
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("address", null);
        subFeature.put("deliveryFee", "10000");

        Payment payment = new Payment("123456789", "cash", subFeature);

        assertTrue(hasNullValue(payment.getPaymentData()));
        assertEquals("REJECT", payment.getStatus());
    }

    @Test
    void testCreatePaymentCashReject_ExistEmptySubFields1() {
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("address", "");
        subFeature.put("deliveryFee", "10000");

        Payment payment = new Payment("123456789", "cash", subFeature);

        assertTrue(hasEmptyValue(payment.getPaymentData()));
        assertEquals("REJECT", payment.getStatus());
    }

    @Test
    void testCreatePaymentCashReject_ExistSubFieldsNull2() {
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("address", "Cipete");
        subFeature.put("deliveryFee", null);

        Payment payment = new Payment("123456789", "cash", subFeature);

        assertTrue(hasNullValue(payment.getPaymentData()));
        assertEquals("REJECT", payment.getStatus());
    }

    @Test
    void testCreatePaymentCashReject_ExistEmptySubFields2() {
        Map<String, String> subFeature = new HashMap<>();
        subFeature.put("address", "Cipete");
        subFeature.put("deliveryFee", "");

        Payment payment = new Payment("123456789", "cash", subFeature);

        assertTrue(hasEmptyValue(payment.getPaymentData()));
        assertEquals("REJECT", payment.getStatus());
    }



}
