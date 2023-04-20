package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Item {
    String item_id;
    String item_name;
    Double item_price;
    String item_qty;
}
