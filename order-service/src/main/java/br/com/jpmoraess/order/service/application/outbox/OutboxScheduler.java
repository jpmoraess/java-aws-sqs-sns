package br.com.jpmoraess.order.service.application.outbox;

import br.com.jpmoraess.order.service.application.ports.output.publisher.OutboxEventPublisher;
import br.com.jpmoraess.order.service.application.ports.output.repository.OutboxEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OutboxScheduler {

    private static final Logger logger = LoggerFactory.getLogger(OutboxScheduler.class);

    private final OutboxEventRepository outboxEventRepository;
    private final OutboxEventPublisher outboxEventPublisher;

    public OutboxScheduler(OutboxEventRepository outboxEventRepository,
                           OutboxEventPublisher outboxEventPublisher) {
        this.outboxEventRepository = outboxEventRepository;
        this.outboxEventPublisher = outboxEventPublisher;
    }

    @Transactional
    @Scheduled(fixedDelayString = "${outbox-scheduler.fixed-delay}",
            initialDelayString = "${outbox-scheduler.initial-delay}")
    public void processOutboxMessages() {
        outboxEventRepository.findByStatus("STARTED")
                .forEach(outboxEvent -> outboxEventPublisher.publish(outboxEvent, this::updateOutboxStatus));
    }

    private void updateOutboxStatus(OutboxEvent outboxEvent, String status) {
        OutboxEvent outboxEventUpdated = outboxEvent.withStatus(status);
        outboxEventRepository.save(outboxEventUpdated);
        logger.info("Outbox event status updated: {}", outboxEventUpdated);
    }
}
