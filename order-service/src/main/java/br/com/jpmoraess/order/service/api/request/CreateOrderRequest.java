package br.com.jpmoraess.order.service.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Schema(name = "CreateOrderRequest", description = "Request to create an order")
public record CreateOrderRequest(
        @NotNull(message = "Customer ID cannot be null")
        @Schema(description = "ID of the customer placing the order", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID customerId,

        @NotNull(message = "Products cannot be null")
        @Schema(description = "List of product IDs in the order", example = "[\"3fa85f64-5717-4562-b3fc-2c963f66afa6\", \"3fa85f64-5717-4562-b3fc-2c963f66afa7\"]")
        List<UUID> products
) {
}
