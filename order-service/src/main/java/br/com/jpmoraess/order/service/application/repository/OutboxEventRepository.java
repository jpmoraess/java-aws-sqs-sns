package br.com.jpmoraess.order.service.application.repository;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;

public interface OutboxEventRepository {

    void save(OutboxEvent outboxEvent);
}
