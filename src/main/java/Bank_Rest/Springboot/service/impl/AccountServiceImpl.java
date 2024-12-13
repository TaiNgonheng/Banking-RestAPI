package Bank_Rest.Springboot.service.impl;

import Bank_Rest.Springboot.dto.AccountDto;
import Bank_Rest.Springboot.entity.Account;
import Bank_Rest.Springboot.exception.AccountException;
import Bank_Rest.Springboot.mapper.AccountMapper;
import Bank_Rest.Springboot.repository.AccountRepository;
import Bank_Rest.Springboot.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account createAccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(createAccount);
    }

    @Override
    public AccountDto getUserById(Long AccountId) {
            Account account = accountRepository.findById(AccountId).orElseThrow(()->new AccountException("this account are not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
         Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("this is nnot exist"));

         double total = account.getBalance()+amount;
         account.setBalance(total);
         Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withDraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("this id are not exist"));
        if (account.getBalance()<amount){
            throw new RuntimeException("Insurfficient amount")
;        }
        double total = account.getBalance()- amount;
        account.setBalance(total);
       Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> account = accountRepository.findAll();
        return account.stream().map((account1)->AccountMapper.mapToAccountDto(account1)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("this account with this Id are not found."));
        accountRepository.deleteById(id);
    }


}
