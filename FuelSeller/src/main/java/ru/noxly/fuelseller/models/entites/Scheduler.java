package ru.noxly.fuelseller.models.entites;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.fuelseller.models.enums.SchedulerEnum;
import ru.noxly.fuelseller.models.enums.SchedulerStatus;

import java.time.OffsetDateTime;

@Entity
@Setter
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "schedulers")
public class Scheduler {

    @Id
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private SchedulerEnum name;

    @Enumerated(EnumType.STRING)
    private SchedulerType type;

    private Boolean enabled;

    private String cronExpression;

    private Long fixedDelay;

    @Basic
    private OffsetDateTime startTime;

    @Enumerated(EnumType.STRING)
    private SchedulerStatus status;

    @Basic
    private OffsetDateTime lastRunAt;

    public enum SchedulerType {
        ONE_TIME,       // Одноразовый запуск
        FIXED_DELAY,    // Периодическая задача с задержкой
        CRON            // Задача по cron-выражению
    }

}
