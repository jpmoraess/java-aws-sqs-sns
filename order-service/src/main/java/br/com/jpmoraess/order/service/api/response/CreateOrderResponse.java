package br.com.jpmoraess.order.service.api.response;

import java.util.UUID;

public record CreateOrderResponse(UUID id) {
    public static CreateOrderResponse of(UUID id) {
        return new CreateOrderResponse(id);
    }
}
