package br.com.jpmoraess.order.service.application.repository;

import br.com.jpmoraess.order.service.domain.entity.Order;

public interface OrderRepository {

    void save(Order order);
}
