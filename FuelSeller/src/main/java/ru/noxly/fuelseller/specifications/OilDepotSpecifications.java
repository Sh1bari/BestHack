package ru.noxly.fuelseller.specifications;

import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import ru.noxly.fuelseller.models.entites.OilDepot;

public class OilDepotSpecifications {

    public static Specification<OilDepot> hasNameLikeIgnoreCase(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); // Не применяем фильтр, возвращаем "истину"
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    // Поиск по региону (LIKE, IGNORE CASE), если параметр не null
    public static Specification<OilDepot> hasRegionLikeIgnoreCase(String region) {
        return (root, query, criteriaBuilder) -> {
            if (region == null || region.isEmpty()) {
                return criteriaBuilder.conjunction(); // Не применяем фильтр
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("region")), "%" + region.toLowerCase() + "%");
        };
    }
}
