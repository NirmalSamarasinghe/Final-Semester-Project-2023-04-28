package model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeTM {
    private String employee_id;
    private String employee_name;
    private String employee_address;
    private String employee_contact;
}
