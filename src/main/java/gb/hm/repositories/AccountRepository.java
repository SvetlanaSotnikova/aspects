package gb.hm.repositories;

import lombok.AllArgsConstructor;
import gb.hm.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import gb.hm.repositories.mapper.AccountMapper;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Repository
public class AccountRepository {
    private final JdbcTemplate jdbc;

    public Account findAccountById(long id) {
        String sql = "select * from account where id = ?";
        return jdbc.queryForObject(sql, new AccountMapper(), id);
    }

    public List<Account> findAll() {
        String sql = "select * from account";
        return jdbc.query(sql, new AccountMapper());
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "update account set amount = ? where id = ?";
        jdbc.update(sql, amount, id);
    }
}
