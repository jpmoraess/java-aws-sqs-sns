package br.com.jpmoraess.order.service.application.usecase;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.ports.input.CreateOrderUseCase;
import br.com.jpmoraess.order.service.application.ports.output.repository.OrderRepository;
import br.com.jpmoraess.order.service.application.ports.output.repository.OutboxEventRepository;
import br.com.jpmoraess.order.service.domain.entity.Order;
import br.com.jpmoraess.order.service.domain.exception.OrderDomainException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateOrderUseCaseImpl.class);

    private static final String ORDER = "Order";
    private static final String ORDER_CREATED = "OrderCreated";

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    private final OutboxEventRepository outboxEventRepository;

    public CreateOrderUseCaseImpl(ObjectMapper objectMapper,
                                  OrderRepository orderRepository,
                                  OutboxEventRepository outboxEventRepository) {
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
        this.outboxEventRepository = outboxEventRepository;
    }

    @Override
    @Transactional
    public CreateOrderOutput execute(CreateOrderInput input) {
        logger.info("Creating order for customer: {}", input.customerId());
        Order order = Order.create(input.customerId(), input.products());
        orderRepository.save(order);

        OutboxEvent outboxEvent = OutboxEvent.create(ORDER, order.getId().toString(), ORDER_CREATED, createPayload(order));
        outboxEventRepository.save(outboxEvent);

        logger.info("Order created: {}", order);
        return CreateOrderOutput.of(order.getId(), order.getCustomerId(), order.getProducts(), order.getTotal());
    }

    private String createPayload(Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            logger.error("Could not create payload object for order id: {}", order.getId(), e);
            throw new OrderDomainException("Could not create payload object for order id: " + order.getId(), e);
        }
    }
}
