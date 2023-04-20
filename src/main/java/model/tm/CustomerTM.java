package model.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CustomerTM {
    private String cus_id;
    private String cus_name;
    private String cus_address;
    private String cus_contact;
    private Button btn;

}
