package br.com.jpmoraess.order.service.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Order {

    private final UUID id;
    private final UUID customerId;
    private final List<UUID> products;
    private final BigDecimal total;

    private Order(UUID id, UUID customerId, List<UUID> products, BigDecimal total) {
        this.id = id;
        this.customerId = customerId;
        this.products = products;
        this.total = total;
    }

    public static Order create(UUID customerId, List<UUID> products) {
        // ADDITIONAL VALIDATIONS CAN BE ADDED HERE
        BigDecimal total = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(50, 350))
                .setScale(2, RoundingMode.HALF_UP);
        return new Order(UUID.randomUUID(), customerId, products, total);
    }

    public static Order restore(UUID id, UUID customerId, List<UUID> products, BigDecimal total) {
        // ADDITIONAL VALIDATIONS CAN BE ADDED HERE
        return new Order(id, customerId, products, total);
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<UUID> getProducts() {
        return products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", products=" + products +
                ", total=" + total +
                '}';
    }
}
