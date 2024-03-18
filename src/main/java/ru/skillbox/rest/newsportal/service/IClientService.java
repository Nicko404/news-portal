package ru.skillbox.rest.newsportal.service;

import ru.skillbox.rest.newsportal.model.Client;
import ru.skillbox.rest.newsportal.web.model.request.ClientFilter;

import java.util.List;

public interface IClientService {

    List<Client> findAll(ClientFilter clientFilter);

    Client findById(Long id);

    Client save(Client client);

    Client update(Client client);

    void deleteById(Long id);
}
