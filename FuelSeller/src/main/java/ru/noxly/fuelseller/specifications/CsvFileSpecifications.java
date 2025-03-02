package ru.noxly.fuelseller.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.noxly.fuelseller.models.entites.CsvFile;
import ru.noxly.fuelseller.models.enums.CsvFileStatus;

public class CsvFileSpecifications {

    public static Specification<CsvFile> hasStatus(CsvFileStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
