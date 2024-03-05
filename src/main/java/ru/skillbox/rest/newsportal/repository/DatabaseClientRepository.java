package ru.skillbox.rest.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.skillbox.rest.newsportal.model.Client;

public interface DatabaseClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
}
