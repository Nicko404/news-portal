package ru.skillbox.rest.newsportal.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.rest.newsportal.mapper.ClientMapper;
import ru.skillbox.rest.newsportal.model.Client;
import ru.skillbox.rest.newsportal.service.IClientService;
import ru.skillbox.rest.newsportal.web.model.request.ClientFilter;
import ru.skillbox.rest.newsportal.web.model.request.UpsertClientRequest;
import ru.skillbox.rest.newsportal.web.model.response.ClientListResponse;
import ru.skillbox.rest.newsportal.web.model.response.ClientResponse;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<ClientListResponse> findAll(@Valid ClientFilter clientFilter) {
        return ResponseEntity.ok(clientMapper.clientListToClientListResponse(clientService.findAll(clientFilter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clientMapper.clientToResponse(clientService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> save(@RequestBody @Valid UpsertClientRequest request) {
        Client savedClient = clientService.save(clientMapper.requestToClient(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientMapper.clientToResponse(savedClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable("id") Long id, @RequestBody @Valid UpsertClientRequest request) {
        Client updatedClient = clientService.update(clientMapper.requestToClient(id, request));
        return ResponseEntity.ok(clientMapper.clientToResponse(updatedClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
