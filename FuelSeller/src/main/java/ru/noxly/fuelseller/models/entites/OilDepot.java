package ru.noxly.fuelseller.models.entites;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "oil_depots")
public class OilDepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column
    private final String name;

    @Column
    private final String region;
}
