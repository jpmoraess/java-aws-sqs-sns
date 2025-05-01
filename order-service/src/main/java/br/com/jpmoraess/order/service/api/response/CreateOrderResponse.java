package br.com.jpmoraess.order.service.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "CreateOrderResponse", description = "Response for creating an order")
public record CreateOrderResponse(
        @Schema(description = "ID of the created order", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID id
) {
    public static CreateOrderResponse of(UUID id) {
        return new CreateOrderResponse(id);
    }
}
