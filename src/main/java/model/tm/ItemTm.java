package model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ItemTm {
    private String item_id;
    private String item_name;
    private String item_price;
    private String item_qty;

}
