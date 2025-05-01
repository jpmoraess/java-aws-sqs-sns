package br.com.jpmoraess.order.service.infrastructure.messaging;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.outbox.OutboxStatus;
import br.com.jpmoraess.order.service.application.ports.output.publisher.OutboxEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class OutboxEventSnsPublisher implements OutboxEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(OutboxEventSnsPublisher.class);

    @Override
    public void publish(OutboxEvent outboxEvent, BiConsumer<OutboxEvent, OutboxStatus> callback) {
        logger.info("Publishing outbox event to SNS: {}", outboxEvent);
        callback.accept(outboxEvent, OutboxStatus.COMPLETED);
    }
}
