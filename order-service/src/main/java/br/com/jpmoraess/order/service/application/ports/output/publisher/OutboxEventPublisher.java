package br.com.jpmoraess.order.service.application.ports.output.publisher;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;

import java.util.function.BiConsumer;

public interface OutboxEventPublisher {

    void publish(OutboxEvent outboxEvent, BiConsumer<OutboxEvent, String> callback);
}
