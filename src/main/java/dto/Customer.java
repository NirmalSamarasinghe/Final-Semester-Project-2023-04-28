package dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Customer {
    private String cus_id;
    private String cus_name;
    private String cus_address;
    private String cus_contact;

}
