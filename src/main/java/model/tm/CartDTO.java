package model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartDTO {
    String item_id;
    Integer qty;
    Double unitPrice;
}
