package alfrendo.challenge2.databases;

import alfrendo.challenge2.models.Order;
import lombok.*;

import java.util.List;

@Setter
@Getter
public class Seeder {

    private final List<Order> orders = List.of(
            new Order("Nasi Goreng", 15_000, 0, ""),
            new Order("Mie Goreng", 13_000, 0, ""),
            new Order("Nasi + Ayam", 18_000, 0, ""),
            new Order("Es Teh Manis", 3_000, 0, ""),
            new Order("Es Jeruk", 5_000, 0, "")
    );

}

