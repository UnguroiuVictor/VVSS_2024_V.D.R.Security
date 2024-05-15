package pizzashop.service;

import pizzashop.PizzaException;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PaymentService {

    private PaymentRepository payRepo;

    public PaymentService(PaymentRepository payRepo) {
        this.payRepo = payRepo;
    }

    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(int table, PaymentType type, double amount) {
        Payment payment = new Payment(table, type, amount);
        if (table < 1 || table > 8)
            throw new PizzaException("Table number must be 1-8");
        if (amount <= 0)
            throw new PizzaException("Amount should be positive");
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;                                           //1
        if (type != PaymentType.Cash && type != PaymentType.Card)      //2
            total = 0;                                                 //3
        else {
            List<Payment> l = getPayments();                           //4
            if (l.size() == 0)                                         //5
                total = 0;                                             //6
            else for (int i = 0; i < l.size(); i++) {                  //7 //8 //12
                Payment p = l.get(i);                                  //9
                if (p.getType().equals(type))                          //10
                    total += p.getAmount();                            //11
            }
        }
        return total;                                                  //13
    }
}
