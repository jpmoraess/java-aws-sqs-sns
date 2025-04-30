package br.com.jpmoraess.order.service.application.outbox;

public record OutboxEvent(
        String aggregateType,
        String aggregateId,
        String eventType,
        String payload,
        String status
) {

    public static OutboxEvent create(String aggregateType, String aggregateId, String eventType, String payload) {
        return new OutboxEvent(aggregateType, aggregateId, eventType, payload, "PENDING");
    }

    public OutboxEvent withStatus(String status) {
        return new OutboxEvent(aggregateType, aggregateId, eventType, payload, status);
    }
}
