package ru.noxly.fuelseller.models.entites;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.fuelseller.models.enums.LotStatus;

import java.time.OffsetDateTime;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "lots")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Basic
    @Column
    private final OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "oil_depot_id")
    private final OilDepot oilDepot;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private final Fuel fuel;

    @Column
    private final Double volumeOfFuel;

    @Column
    private final Double remainingFuel;

    @Enumerated(EnumType.STRING)
    @Column
    private final LotStatus status;

    @Column
    private final Double totalPrice;

    @Column
    private final Double pricePerTon;
}
