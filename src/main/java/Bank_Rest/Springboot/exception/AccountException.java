package Bank_Rest.Springboot.exception;

public class AccountException extends RuntimeException{
    public AccountException(String message){
        super(message);
    }
}
