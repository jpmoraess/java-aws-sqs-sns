package br.com.jpmoraess.order.service.application.outbox;

import java.util.UUID;

public record OutboxEvent(
        UUID id,
        String aggregateType,
        String aggregateId,
        String eventType,
        String payload,
        OutboxStatus status
) {

    public static OutboxEvent create(String aggregateType, String aggregateId, String eventType, String payload) {
        return new OutboxEvent(UUID.randomUUID(), aggregateType, aggregateId, eventType, payload, OutboxStatus.STARTED);
    }

    public OutboxEvent withStatus(OutboxStatus status) {
        return new OutboxEvent(id, aggregateType, aggregateId, eventType, payload, status);
    }
}
