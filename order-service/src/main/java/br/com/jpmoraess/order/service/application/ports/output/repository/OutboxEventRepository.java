package br.com.jpmoraess.order.service.application.ports.output.repository;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;

import java.util.List;

public interface OutboxEventRepository {

    void save(OutboxEvent outboxEvent);

    List<OutboxEvent> findByStatus(String status);
}
