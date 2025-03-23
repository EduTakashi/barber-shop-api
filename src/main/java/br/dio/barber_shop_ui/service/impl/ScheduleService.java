package br.dio.barber_shop_ui.service.impl;

import br.dio.barber_shop_ui.entity.ScheduleEntity;
import br.dio.barber_shop_ui.repository.IScheduleRepository;
import br.dio.barber_shop_ui.service.IScheduleService;
import br.dio.barber_shop_ui.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return repository.save(entity);
    }

    @Override
    public void delete(long id) {
        queryService.findyById(id);
        repository.deleteById(id);

    }
}
