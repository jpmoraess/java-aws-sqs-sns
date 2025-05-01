package br.com.jpmoraess.order.service.application.ports.output.publisher;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface OutboxEventPublisher {

    void publish(OutboxEvent outboxEvent, BiConsumer<OutboxEvent, OutboxStatus> callback);
}
