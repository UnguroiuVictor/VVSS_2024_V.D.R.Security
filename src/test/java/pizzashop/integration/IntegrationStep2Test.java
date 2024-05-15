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

class IntegrationStep2Test {
    private PaymentRepository repo;
    private PaymentService srv;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        payment1 = Mockito.mock(Payment.class);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment1.getAmount()).thenReturn(1.0);

        payment2 = Mockito.mock(Payment.class);
        Mockito.when(payment2.getType()).thenReturn(PaymentType.Card);
        Mockito.when(payment2.getAmount()).thenReturn(2.0);

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
        repo.add(payment1);
        repo.add(payment2);
        List<Payment> payments = srv.getPayments();

        assertEquals(2, payments.size());
    }

    @Test
    void getTotalAmount() {
        repo.add(payment1);
        repo.add(payment2);
        double totalAmount = srv.getTotalAmount(PaymentType.Cash);

        assertEquals(1, totalAmount);
    }
}