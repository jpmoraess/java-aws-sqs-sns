package br.com.jpmoraess.order.service.infrastructure.persistence.order;

import br.com.jpmoraess.order.service.application.repository.OrderRepository;
import br.com.jpmoraess.order.service.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = OrderDataMapper.toEntity(order);
        orderJpaRepository.save(orderEntity);
    }
}
