package ru.noxly.fuelseller.repositories;

import lombok.*;
import ru.noxly.fuelseller.models.entites.CsvFile;
import ru.sh1bari.resolver.BaseJpaRepository;

public interface CsvFileRepository extends BaseJpaRepository<CsvFile, Long> {
}
