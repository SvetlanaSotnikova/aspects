package gb.hm.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private long senderAccountID;
    private long receiverAccountID;
    private BigDecimal amount;
}
