package ru.skillbox.rest.newsportal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.rest.newsportal.model.Client;
import ru.skillbox.rest.newsportal.web.model.request.UpsertClientRequest;
import ru.skillbox.rest.newsportal.web.model.response.ClientListResponse;
import ru.skillbox.rest.newsportal.web.model.response.ClientResponse;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
public interface ClientMapper {

    Client requestToClient(UpsertClientRequest request);

    @Mapping(source = "clientId", target = "id")
    Client requestToClient(Long clientId, UpsertClientRequest request);

    ClientResponse clientToResponse(Client client);

    default ClientListResponse clientListToClientListResponse(List<Client> clientList) {
        ClientListResponse response = new ClientListResponse();

        response.setClients(clientList.stream()
                .map(this::clientToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
