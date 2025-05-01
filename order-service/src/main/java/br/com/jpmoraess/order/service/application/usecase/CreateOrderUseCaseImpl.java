package br.com.jpmoraess.order.service.application.usecase;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.ports.input.CreateOrderUseCase;
import br.com.jpmoraess.order.service.application.ports.output.repository.OrderRepository;
import br.com.jpmoraess.order.service.application.ports.output.repository.OutboxEventRepository;
import br.com.jpmoraess.order.service.domain.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateOrderUseCaseImpl.class);

    private static final String ORDER = "Order";
    private static final String ORDER_CREATED = "OrderCreated";

    private final OrderRepository orderRepository;
    private final OutboxEventRepository outboxEventRepository;

    public CreateOrderUseCaseImpl(OrderRepository orderRepository, OutboxEventRepository outboxEventRepository) {
        this.orderRepository = orderRepository;
        this.outboxEventRepository = outboxEventRepository;
    }

    @Override
    @Transactional
    public CreateOrderOutput execute(CreateOrderInput input) {
        logger.info("Creating order for customer: {}", input.customerId());
        Order order = Order.create(input.customerId(), input.products());
        orderRepository.save(order);

        OutboxEvent outboxEvent = OutboxEvent
                .create(ORDER, order.getId().toString(), ORDER_CREATED, order.toString());
        outboxEventRepository.save(outboxEvent);

        logger.info("Order created: {}", order);
        return CreateOrderOutput.of(order.getId(), order.getCustomerId(), order.getProducts(), order.getTotal());
    }
}
