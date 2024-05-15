package pizzashop.unit;

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

class PaymentServiceTest {
    List<Payment> currentPayments = List.of(new Payment(1, PaymentType.Cash, 1), new Payment(2, PaymentType.Card, 3));
    private PaymentRepository repo;
    private PaymentService srv;

    @BeforeEach
    void setUp() {
        repo = Mockito.mock(PaymentRepository.class);
        Mockito.when(repo.getAll()).thenReturn(currentPayments);
        srv = new PaymentService(repo);
    }

    @Test
    void getPayments() {
        List<Payment> payments = srv.getPayments();

        assertEquals(currentPayments.size(), payments.size());
    }

    @Test
    void getTotalAmount() {
        double totalAmount = srv.getTotalAmount(PaymentType.Cash);

        assertEquals(1, totalAmount);
    }
}