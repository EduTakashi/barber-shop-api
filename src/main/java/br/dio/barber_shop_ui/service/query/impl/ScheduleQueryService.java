package br.dio.barber_shop_ui.service.query.impl;

import br.dio.barber_shop_ui.entity.ScheduleEntity;
import br.dio.barber_shop_ui.exception.NotFoundException;
import br.dio.barber_shop_ui.exception.ScheduleInUseException;
import br.dio.barber_shop_ui.repository.IScheduleRepository;
import br.dio.barber_shop_ui.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findyById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
    }

    @Override
    public List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if(repository.existsByStartAtAndEndAt(startAt, endAt)){
            var message = "Horário já foi agendado por outro cliente";
            throw new ScheduleInUseException(message);
        }

    }
}
