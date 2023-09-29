package alfrendo.challenge2.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private String name;

    private Integer price;

    private Integer quantity;

    private String notes;

}
