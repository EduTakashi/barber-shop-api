package br.dio.barber_shop_ui.service.query.impl;

import br.dio.barber_shop_ui.entity.ClientEntity;
import br.dio.barber_shop_ui.exception.EmailInUseException;
import br.dio.barber_shop_ui.exception.NotFoundException;
import br.dio.barber_shop_ui.exception.PhoneInUseException;
import br.dio.barber_shop_ui.repository.IClientRepository;
import br.dio.barber_shop_ui.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class ClientQueryService  implements IClientQueryService {

    private final IClientRepository repository;

    @Override
    public ClientEntity findById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente " + id + " não encontrado"));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if (repository.existsByPhone(phone)){
            var message = "Esse número de telefone já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(long id, String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(),phone)){
            var message = "Esse número de telefone já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if (repository.existsByEmail(email)){
            var message = "Esse email já está em uso";
            throw new EmailInUseException(message);
        }
    }

    @Override
    public void verifyEmail(long id, String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getEmail(),email)){
            var message = "Esse email já está em uso";
            throw new EmailInUseException(message);
        }
    }
}
