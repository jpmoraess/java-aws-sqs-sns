package br.com.jpmoraess.order.service.application.usecase;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.repository.OrderRepository;
import br.com.jpmoraess.order.service.application.repository.OutboxEventRepository;
import br.com.jpmoraess.order.service.domain.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class CreateOrderUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateOrderUseCase.class);

    private static final String ORDER = "Order";
    private static final String ORDER_CREATED = "OrderCreated";

    private final OrderRepository orderRepository;
    private final OutboxEventRepository outboxEventRepository;

    public CreateOrderUseCase(OrderRepository orderRepository, OutboxEventRepository outboxEventRepository) {
        this.orderRepository = orderRepository;
        this.outboxEventRepository = outboxEventRepository;
    }

    @Transactional
    public CreateOrderOutput execute(CreateOrderInput input) {
        logger.info("Creating order for customer: {}", input.customerId());
        Order order = Order.create(input.customerId(), input.products());
        orderRepository.save(order);

        OutboxEvent outboxEvent = OutboxEvent
                .create(ORDER, order.getId().toString(), ORDER_CREATED, order.toString());
        outboxEventRepository.save(outboxEvent);

        logger.info("Order created: {}", order);
        return new CreateOrderOutput(order.getId(), order.getCustomerId(), order.getProducts(), order.getTotal());
    }

    public record CreateOrderInput(UUID customerId, List<UUID> products) {
    }

    public record CreateOrderOutput(UUID id, UUID customerId, List<UUID> products, BigDecimal total) {
    }
}
