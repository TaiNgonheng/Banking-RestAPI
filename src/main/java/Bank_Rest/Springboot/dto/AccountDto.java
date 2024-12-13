package Bank_Rest.Springboot.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class AccountDto {
//    private Long id;
//    private String accountHolderName;
//    private double balance;
//
//    // No manually written constructors needed
//}
public record AccountDto(
        Long id,
        String accountHolderName,
        double balance) {
}
