package model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {

    private String cusId;
    private String name;
    private String address;
    private String email;

}
