package gb.hm.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private long id;
    private String name;
    private BigDecimal amount;
}
