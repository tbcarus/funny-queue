package ru.tbcarus.funnyqueue.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "slots")
public class Slot extends AbstractBaseEntity {

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "queue_id", nullable = false)
    private Queue queue;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date_time", unique = true)
    private LocalDateTime dateTime;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
