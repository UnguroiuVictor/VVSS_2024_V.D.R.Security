package pizzashop.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PaymentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationStep3Test {
    private PaymentRepository repo;
    private PaymentService srv;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        payment1 = new Payment(1, PaymentType.Cash, 1);

        payment2 = Mockito.mock(Payment.class);

        repo = new PaymentRepository();
        srv = new PaymentService(repo);
        repo.clear();
    }

    @AfterEach
    void tearDown() {
        repo.clear();
        repo.writeAll();
    }

    @Test
    void getPayments() {
        srv.addPayment(1, PaymentType.Cash, 1);
        srv.addPayment(2, PaymentType.Cash, 2);
        List<Payment> payments = srv.getPayments();

        assertEquals(2, payments.size());
    }

    @Test
    void getTotalAmount() {
        srv.addPayment(1, PaymentType.Cash, 1);
        srv.addPayment(2, PaymentType.Cash, 2);
        double totalAmount = srv.getTotalAmount(PaymentType.Cash);

        assertEquals(3, totalAmount);
    }
}