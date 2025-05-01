package br.com.jpmoraess.order.service.application.ports.output.repository;

import br.com.jpmoraess.order.service.domain.entity.Order;

public interface OrderRepository {

    void save(Order order);
}
