package br.com.jpmoraess.order.service.api.request;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        UUID customerId,
        List<UUID> products
) {
    public static CreateOrderRequest of(UUID customerId, List<UUID> products) {
        return new CreateOrderRequest(customerId, products);
    }
}
