package br.dio.barber_shop_ui.service;

import br.dio.barber_shop_ui.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);
}
