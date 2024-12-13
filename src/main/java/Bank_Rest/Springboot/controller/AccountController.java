package Bank_Rest.Springboot.controller;


import Bank_Rest.Springboot.dto.AccountDto;
import Bank_Rest.Springboot.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.createAccount(accountDto));
    }

    //delete account rest api
    @DeleteMapping("/{id}/delete")
     public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is delete SuccessFully");
     }


    @GetMapping("/{id}/get")
    public ResponseEntity<AccountDto> getUser(@PathVariable("id") Long AccountId){
        return ResponseEntity.ok(accountService.getUserById(AccountId));
    }

    //Deposit RestAPI
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAccount(@PathVariable("id") Long id,@RequestBody Map<String, Double> request){
        double amount = request.get("amount");
      AccountDto accountDto=  accountService.deposit(id,amount);
      return ResponseEntity.ok(accountDto);
    }

    //withdraw rest api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withDraw(@PathVariable("id") Long id,@RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withDraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        List<AccountDto> getAll = accountService.getAllAccount();
        return ResponseEntity.ok(getAll);
    }
}

