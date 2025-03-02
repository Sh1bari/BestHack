package ru.noxly.fuelseller.models.entites;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.fuelseller.models.enums.DeliveryType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Basic
    @Column
    private final OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private final Lot lot;

    @ManyToOne
    @JoinColumn(name = "oil_depot_id")
    private final OilDepot oilDepot;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private final Fuel fuel;

    @Column
    private final Double volume; // количество купленного топлива

    @Enumerated(EnumType.STRING)
    @Column
    private final DeliveryType deliveryType;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private final Client client;
}
