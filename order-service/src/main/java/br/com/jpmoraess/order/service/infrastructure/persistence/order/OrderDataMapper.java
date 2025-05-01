package br.com.jpmoraess.order.service.infrastructure.persistence.order;

import br.com.jpmoraess.order.service.domain.entity.Order;

public class OrderDataMapper {

    public static OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getCustomerId(),
                order.getProducts(),
                order.getTotal()
        );
    }
}
