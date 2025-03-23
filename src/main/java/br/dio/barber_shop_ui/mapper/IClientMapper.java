package br.dio.barber_shop_ui.mapper;

import br.dio.barber_shop_ui.controller.request.SaveClientRequest;
import br.dio.barber_shop_ui.controller.request.UpdateClientRequest;
import br.dio.barber_shop_ui.controller.response.ClientDetailResponse;
import br.dio.barber_shop_ui.controller.response.ListClientResponse;
import br.dio.barber_shop_ui.controller.response.SaveClientResponse;
import br.dio.barber_shop_ui.controller.response.UpdateClientResponse;
import br.dio.barber_shop_ui.entity.ClientEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IClientMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "schedules",ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);
}
