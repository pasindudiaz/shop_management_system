package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetails {
    private String orderId;
    private String itemId;
    private Integer quantity;
    private Integer discount;
}
