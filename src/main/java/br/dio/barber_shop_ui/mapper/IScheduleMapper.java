package br.dio.barber_shop_ui.mapper;

import br.dio.barber_shop_ui.controller.request.SaveScheduleRequest;
import br.dio.barber_shop_ui.controller.response.ClientScheduleAppointmentResponse;
import br.dio.barber_shop_ui.controller.response.SaveScheduleResponse;
import br.dio.barber_shop_ui.controller.response.ScheduleAppointmentMonthResponse;
import br.dio.barber_shop_ui.entity.ScheduleEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper {


    @Mapping(target = "id",ignore = true)
    @Mapping(target = "client.id",source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId",source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    @Mapping(target = "scheduledAppointments",expression = "java(toClientMonthResponse(entities))       ")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year,final  int month,final List<ScheduleEntity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);

}
