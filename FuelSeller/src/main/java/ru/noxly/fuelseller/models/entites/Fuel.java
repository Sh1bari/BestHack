package ru.noxly.fuelseller.models.entites;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.fuelseller.models.enums.FuelType;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "fuels")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private final FuelType fuelType;
}
