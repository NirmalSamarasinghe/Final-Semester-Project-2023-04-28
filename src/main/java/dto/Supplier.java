package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Supplier {
    private String supplier_id;
    private String supplier_name;
    private String supplier_contact;
    private String supplier_company_name;
}
