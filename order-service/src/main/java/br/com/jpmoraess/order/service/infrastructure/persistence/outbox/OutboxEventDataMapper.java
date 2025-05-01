package br.com.jpmoraess.order.service.infrastructure.persistence.outbox;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;

public class OutboxEventDataMapper {

    public static OutboxEventEntity toEntity(OutboxEvent outboxEvent) {
        return new OutboxEventEntity(
                outboxEvent.id(),
                outboxEvent.aggregateType(),
                outboxEvent.aggregateId(),
                outboxEvent.eventType(),
                outboxEvent.payload(),
                outboxEvent.status()
        );
    }

    public static OutboxEvent toEvent(OutboxEventEntity outboxEventEntity) {
        return new OutboxEvent(
                outboxEventEntity.getId(),
                outboxEventEntity.getAggregateType(),
                outboxEventEntity.getAggregateId(),
                outboxEventEntity.getEventType(),
                outboxEventEntity.getPayload(),
                outboxEventEntity.getStatus()
        );
    }
}
