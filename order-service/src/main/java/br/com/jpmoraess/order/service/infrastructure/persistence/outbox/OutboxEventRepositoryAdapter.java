package br.com.jpmoraess.order.service.infrastructure.persistence.outbox;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.repository.OutboxEventRepository;
import org.springframework.stereotype.Component;

@Component
public class OutboxEventRepositoryAdapter implements OutboxEventRepository {

    private final OutboxEventJpaRepository outboxEventJpaRepository;

    public OutboxEventRepositoryAdapter(OutboxEventJpaRepository outboxEventJpaRepository) {
        this.outboxEventJpaRepository = outboxEventJpaRepository;
    }

    @Override
    public void save(OutboxEvent outboxEvent) {
        OutboxEventEntity outboxEventEntity = OutboxEventDataMapper.toEntity(outboxEvent);
        outboxEventJpaRepository.save(outboxEventEntity);
    }
}
