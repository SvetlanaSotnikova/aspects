package gb.hm.services;

import gb.hm.aspects.ToLog;
import gb.hm.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import gb.hm.model.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class TransferService {
    private final AccountRepository accountRepository;

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        if (sender == null || receiver == null) {
            throw new RuntimeException("oh no!, something went wrong");
        }

        if (sender.getAmount().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds.");
        }

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);
    }

    @ToLog
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
