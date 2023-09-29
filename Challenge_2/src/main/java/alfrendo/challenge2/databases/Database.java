package alfrendo.challenge2.databases;

import alfrendo.challenge2.models.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Database {

    private final Seeder seeder = new Seeder();

    private final List<Order> orders = seeder.getOrders();

    public Order getOrderByIndex(int index) {
        return orders.get(index);
    }
}
