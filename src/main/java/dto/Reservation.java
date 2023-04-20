package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class Reservation {
    private String reservation_id;
    private String reservation_customerName;
    private int chair_counts;
    private String duration_time;
}
