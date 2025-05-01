package br.com.jpmoraess.order.service.infrastructure.messaging;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.outbox.OutboxStatus;
import br.com.jpmoraess.order.service.application.ports.output.publisher.OutboxEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class OutboxEventSQSPublisher implements OutboxEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(OutboxEventSQSPublisher.class);

    @Override
    public void publish(OutboxEvent outboxEvent, BiConsumer<OutboxEvent, OutboxStatus> callback) {
        logger.info("Publishing outbox event to SQS: {}", outboxEvent);
        callback.accept(outboxEvent, OutboxStatus.COMPLETED);
    }
}
