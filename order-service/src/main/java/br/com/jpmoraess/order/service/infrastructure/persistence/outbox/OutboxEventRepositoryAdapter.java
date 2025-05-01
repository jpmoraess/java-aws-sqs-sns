package br.com.jpmoraess.order.service.infrastructure.persistence.outbox;

import br.com.jpmoraess.order.service.application.outbox.OutboxEvent;
import br.com.jpmoraess.order.service.application.ports.output.repository.OutboxEventRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<OutboxEvent> findByStatus(String status) {
        return outboxEventJpaRepository.findByStatus(status)
                .stream()
                .map(OutboxEventDataMapper::toEvent)
                .toList();
    }
}
