package ru.noxly.fuelseller.models.entites;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.fuelseller.models.enums.CsvFileStatus;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "csv_files")
public class CsvFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column
    private final String name;

    @Column
    @Enumerated(EnumType.STRING)
    private final CsvFileStatus status;
}
