package ru.skillbox.rest.newsportal.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.rest.newsportal.model.Client;
import ru.skillbox.rest.newsportal.web.model.request.ClientFilter;

import java.util.Objects;

public interface ClientSpecification {

    static Specification<Client> withFilter(ClientFilter clientFilter) {
        return Specification.where(byName(clientFilter.getName()));
    }

    static Specification<Client> byName(String name) {
        return (root, query, cb) -> {
            if (Objects.isNull(name)) return null;

            return cb.equal(root.get("name"), name);
        };
    }
}
