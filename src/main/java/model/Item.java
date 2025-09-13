package model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Item {

    private String itemId;
    private String name;
    private  String description;
    private  Integer unitPrice;
    private  Integer quantity;
}
