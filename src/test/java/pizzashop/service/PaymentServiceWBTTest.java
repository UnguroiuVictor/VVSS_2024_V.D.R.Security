package pizzashop.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

class PaymentServiceWBTTest {
    private PaymentService srv;

    @BeforeEach
    void setUp() {
        PaymentRepository repo = new PaymentRepository();
        repo.clear();
        srv = new PaymentService(repo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalAmount1() {
        srv.addPayment(1, PaymentType.Cash, 10);
        srv.addPayment(2, PaymentType.Cash, 3);
        srv.addPayment(5, PaymentType.Card, 4.5);

        double total = srv.getTotalAmount(null);

        assertEquals(total, 0);
    }

    @Test
    void getTotalAmount2() {
        double total = srv.getTotalAmount(PaymentType.Cash);

        assertEquals(total, 0);
    }

    @Test
    void getTotalAmount3() {
        srv.addPayment(1, PaymentType.Cash, 10);
        srv.addPayment(2, PaymentType.Cash, 3);
        srv.addPayment(5, PaymentType.Card, 4.5);

        double total = srv.getTotalAmount(PaymentType.Card);

        assertEquals(total, 4.5);
    }
}
