package br.com.jpmoraess.order.service.infrastructure.messaging;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.outbox.OutboxStatus;
import br.com.jpmoraess.order.service.application.ports.output.publisher.OutboxEventPublisher;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class OutboxEventSQSPublisher implements OutboxEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(OutboxEventSQSPublisher.class);

    private final String queueUrl;
    private final SqsTemplate sqsTemplate;


    public OutboxEventSQSPublisher(@Value("${app.sqs.queue-url}") String queueUrl, SqsTemplate sqsTemplate) {
        this.queueUrl = queueUrl;
        this.sqsTemplate = sqsTemplate;
    }

    @Override
    public void publish(OutboxEvent outboxEvent, BiConsumer<OutboxEvent, OutboxStatus> callback) {
        logger.info("Publishing outbox event to SQS: {}", outboxEvent.id());
        sqsTemplate.sendAsync(queueUrl, outboxEvent.payload())
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        logger.error("Failed to publish outbox event: [{}]", outboxEvent.id(), ex);
                        callback.accept(outboxEvent, OutboxStatus.FAILED);
                    } else {
                        logger.info("Successfully published outbox event: [{}]", outboxEvent.id());
                        callback.accept(outboxEvent, OutboxStatus.COMPLETED);
                    }
                });
    }
}
