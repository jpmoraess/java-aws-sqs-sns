package br.com.jpmoraess.order.service.application.outbox;

import java.util.UUID;

public record OutboxEvent(
        UUID id,
        String aggregateType,
        String aggregateId,
        String eventType,
        String payload,
        String status
) {

    public static OutboxEvent create(String aggregateType, String aggregateId, String eventType, String payload) {
        return new OutboxEvent(UUID.randomUUID(), aggregateType, aggregateId, eventType, payload, "PENDING");
    }

    public OutboxEvent withStatus(String status) {
        return new OutboxEvent(id, aggregateType, aggregateId, eventType, payload, status);
    }
}
