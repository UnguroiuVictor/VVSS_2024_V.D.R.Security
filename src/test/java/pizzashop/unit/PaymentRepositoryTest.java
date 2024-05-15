package pizzashop.unit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private Payment payment;
    private PaymentRepository repo;

    @BeforeEach
    void setUp() {
        payment = Mockito.mock(Payment.class);
        repo = new PaymentRepository();
    }

    @AfterEach
    void tearDown() {
        repo.clear();
        repo.writeAll();
    }

    @Test
    void clear() {
        repo.add(payment);

        repo.clear();

        assertEquals(0, repo.getAll().size());
    }

    @Test
    void add() {
        repo.clear();

        repo.add(payment);

        assertEquals(1, repo.getAll().size());
    }
}