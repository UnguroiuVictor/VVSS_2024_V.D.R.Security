package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.PizzaException;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService srv;

    @AfterEach
    void tearDown() {
    }

    @BeforeEach
    void setUp() {
        PaymentRepository repo = new PaymentRepository();
        srv = new PaymentService(repo);
    }

    @Test
    @DisplayName("ECP1")
    void addPaymentsCash() {
        double amountBefore = srv.getTotalAmount(PaymentType.Cash);
        srv.addPayment(4, PaymentType.Cash, 10);

        double amountAfter = srv.getTotalAmount(PaymentType.Cash);

        assertEquals(amountAfter, amountBefore + 10);
    }

    @RepeatedTest(value = 2)
    @DisplayName("ECP2")
    void addPaymentCard() {
        double amount = 15.7;
        double amountBefore = srv.getTotalAmount(PaymentType.Card);
        srv.addPayment(7, PaymentType.Card, amount);

        double amountAfter = srv.getTotalAmount(PaymentType.Card);

        assertEquals(amountAfter, amountBefore + amount);
    }

    @Test
    @Tag("InvalidTest")
    @DisplayName("ECP3")
    void addPaymentWrongTable() {
        assertThrows(PizzaException.class, () -> srv.addPayment(0, PaymentType.Cash, 15));
    }

    @Test
    @Tag("InvalidTest")
    @DisplayName("ECP4")
    void addPaymentNegativeAmount() {
        assertThrows(PizzaException.class, () -> srv.addPayment(3, PaymentType.Cash, -2.5));
    }

    @Test
    @DisplayName("BVA1 - table is 1 (valid)")
    void addPaymentValidTable() {
        double amount = 5;
        double amountBefore = srv.getTotalAmount(PaymentType.Card);
        srv.addPayment(1, PaymentType.Card, amount);

        double amountAfter = srv.getTotalAmount(PaymentType.Card);

        assertEquals(amountAfter, amountBefore + amount);
    }

    @Test
    @DisplayName("BVA2 - table is 8 (valid)")
    void addPaymentValidTable2() {
        double amount = 5;
        double amountBefore = srv.getTotalAmount(PaymentType.Card);
        srv.addPayment(8, PaymentType.Card, amount);

        double amountAfter = srv.getTotalAmount(PaymentType.Card);

        assertEquals(amountAfter, amountBefore + amount);
    }

    @Test
    @DisplayName("BVA3 - table is 0 (invalid)")
    void addPaymentInvalidTable() {
        assertThrows(PizzaException.class, () -> srv.addPayment(0, PaymentType.Cash, 6));
    }

    @Test
    @DisplayName("BVA4 - table is 9 (invalid)")
    void addPaymentInvalidTable2() {
        assertThrows(PizzaException.class, () -> srv.addPayment(9, PaymentType.Cash, 6));
    }
}