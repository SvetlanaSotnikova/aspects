package gb.hm.controllers;


import gb.hm.dto.TransferRequest;
import gb.hm.services.TransferService;
import lombok.AllArgsConstructor;
import gb.hm.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController("/account")
public class AccountController {
    private final TransferService transferService;

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getSenderAccountID(),
                request.getReceiverAccountID(),
                request.getAmount()
        );
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return transferService.getAllAccounts();
    }
}
