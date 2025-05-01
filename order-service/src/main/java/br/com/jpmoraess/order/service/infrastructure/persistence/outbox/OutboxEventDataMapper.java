package br.com.jpmoraess.order.service.infrastructure.persistence.outbox;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.outbox.OutboxStatus;

public class OutboxEventDataMapper {

    public static OutboxEventEntity toEntity(OutboxEvent outboxEvent) {
        return new OutboxEventEntity(
                outboxEvent.id(),
                outboxEvent.aggregateType(),
                outboxEvent.aggregateId(),
                outboxEvent.eventType(),
                outboxEvent.payload(),
                outboxEvent.status().name()
        );
    }

    public static OutboxEvent toEvent(OutboxEventEntity outboxEventEntity) {
        return new OutboxEvent(
                outboxEventEntity.getId(),
                outboxEventEntity.getAggregateType(),
                outboxEventEntity.getAggregateId(),
                outboxEventEntity.getEventType(),
                outboxEventEntity.getPayload(),
                OutboxStatus.valueOf(outboxEventEntity.getStatus())
        );
    }
}
