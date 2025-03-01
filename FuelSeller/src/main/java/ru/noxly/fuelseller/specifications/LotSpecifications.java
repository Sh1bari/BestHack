package ru.noxly.fuelseller.specifications;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.noxly.fuelseller.models.enums.LotStatus;
import ru.noxly.fuelseller.models.models.requests.GetLotsReq;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LotSpecifications {
    // Фильтр по статусу
    public static Specification<Lot> hasStatus(Set<LotStatus> status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null || status.isEmpty()) {
                return criteriaBuilder.conjunction(); // Возвращаем пустой предикат (не фильтруем)
            }
            return root.get("status").in(status); // Проверяем, что статус входит в переданный Set
        };
    }

    // Фильтр по имени нефтебазы (LIKE, ignoreCase)
    public static Specification<Lot> hasOilDepotName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("oilDepot").get("name")),
                    "%" + name.toLowerCase() + "%");
        };
    }

    // Фильтр по региону нефтебазы (LIKE, ignoreCase)
    public static Specification<Lot> hasOilDepotRegion(String region) {
        return (root, query, criteriaBuilder) -> {
            if (region == null || region.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("oilDepot").get("region")),
                    "%" + region.toLowerCase() + "%");
        };
    }

    // Фильтр по цене (между pricePerTonLower и pricePerTonMore)
    public static Specification<Lot> hasPricePerTonBetween(Double pricePerTonLower, Double pricePerTonMore) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (pricePerTonLower != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("pricePerTon"), pricePerTonLower));
            }

            if (pricePerTonMore != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("pricePerTon"), pricePerTonMore));
            }

            return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    // Фильтр по kssFuel (если передан, должен быть равен)
    public static Specification<Lot> hasKssFuel(Long kssFuel) {
        return (root, query, criteriaBuilder) -> {
            if (kssFuel == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("fuel").get("id"), kssFuel);
        };
    }

    // Композитная спецификация (объединяет все фильтры)
    public static Specification<Lot> withFilters(GetLotsReq request) {
        return Specification.where(hasStatus(request.getLotStatus()))
                .and(hasOilDepotName(request.getOilDepotName()))
                .and(hasOilDepotRegion(request.getOilDepotRegion()))
                .and(hasPricePerTonBetween(request.getPricePerTonLower(), request.getPricePerTonMore()))
                .and(hasKssFuel(request.getKssFuel()));
    }
}
