package br.com.jpmoraess.order.service.application.ports.input;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CreateOrderUseCase {

    CreateOrderOutput execute(CreateOrderInput input);

    record CreateOrderInput(UUID customerId, List<UUID> products) {
        public static CreateOrderInput of(UUID customerId, List<UUID> products) {
            return new CreateOrderInput(customerId, products);
        }
    }

    record CreateOrderOutput(UUID id, UUID customerId, List<UUID> products, BigDecimal total) {
        public static CreateOrderOutput of(UUID id, UUID customerId, List<UUID> products, BigDecimal total) {
            return new CreateOrderOutput(id, customerId, products, total);
        }
    }
}
