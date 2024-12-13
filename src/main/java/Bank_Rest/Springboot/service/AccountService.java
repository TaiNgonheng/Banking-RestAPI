package Bank_Rest.Springboot.service;

import Bank_Rest.Springboot.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getUserById(Long AccountId);
    AccountDto deposit(Long id, double amount);
    AccountDto withDraw(Long id, double amount);
    List<AccountDto> getAllAccount();
    void deleteAccount(Long id);
}
