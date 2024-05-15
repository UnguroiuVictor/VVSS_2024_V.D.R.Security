package pizzashop.unit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    Payment payment;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTableNumber() {
        payment = new Payment(1, PaymentType.Cash, 1);

        assertEquals(1, payment.getTableNumber());
    }

    @Test
    void setTableNumber() {
        payment = new Payment(1, PaymentType.Cash, 1);
        payment.setTableNumber(2);

        assertEquals(2, payment.getTableNumber());
    }

    @Test
    void getType() {
        payment = new Payment(1, PaymentType.Cash, 1);

        assertEquals(PaymentType.Cash, payment.getType());
    }

    @Test
    void setType() {
        payment = new Payment(1, PaymentType.Cash, 1);
        payment.setType(PaymentType.Card);

        assertEquals(PaymentType.Card, payment.getType());
    }

    @Test
    void getAmount() {
        payment = new Payment(1, PaymentType.Cash, 5);

        assertEquals(5, payment.getAmount());
    }

    @Test
    void setAmount() {
        payment = new Payment(1, PaymentType.Cash, 5);
        payment.setAmount(10);

        assertEquals(10, payment.getAmount());
    }

    @Test
    void testToString() {
        payment = new Payment(1, PaymentType.Cash, 5);

        assertEquals("1,Cash,5.0", payment.toString());
    }
}