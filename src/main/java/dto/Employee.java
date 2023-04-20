package dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Employee {
    private String employee_id;
    private String employee_name;
    private String employee_address;
    private String employee_contact;
}
