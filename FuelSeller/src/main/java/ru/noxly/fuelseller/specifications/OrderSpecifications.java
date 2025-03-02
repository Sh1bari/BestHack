package ru.noxly.fuelseller.specifications;

import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import ru.noxly.fuelseller.models.entites.CsvFile;
import ru.noxly.fuelseller.models.entites.Order;
import ru.noxly.fuelseller.models.enums.CsvFileStatus;

import java.util.UUID;

public class OrderSpecifications {

    public static Specification<Order> hasClientId(UUID clientId) {
        return (root, query, criteriaBuilder) -> {
            if (clientId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("client").get("id"), clientId);
        };
    }
}
