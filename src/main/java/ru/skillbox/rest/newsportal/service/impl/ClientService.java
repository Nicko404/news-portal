package ru.skillbox.rest.newsportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skillbox.rest.newsportal.exception.EntityNotFoundException;
import ru.skillbox.rest.newsportal.model.Client;
import ru.skillbox.rest.newsportal.repository.ClientSpecification;
import ru.skillbox.rest.newsportal.repository.DatabaseClientRepository;
import ru.skillbox.rest.newsportal.service.IClientService;
import ru.skillbox.rest.newsportal.utils.BeanUtils;
import ru.skillbox.rest.newsportal.web.model.request.ClientFilter;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final DatabaseClientRepository clientRepository;

    @Override
    public List<Client> findAll(ClientFilter clientFilter) {
        return clientRepository.findAll(ClientSpecification.withFilter(clientFilter), PageRequest.of(
                clientFilter.getPageNumber(), clientFilter.getPageSize()
        )).getContent();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Клиент с ID {0} не найден!", id)
                ));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client existedClient = findById(client.getId());

        BeanUtils.copyNonNullProperties(client, existedClient);

        return clientRepository.save(existedClient);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
