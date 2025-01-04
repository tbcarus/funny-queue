package ru.tbcarus.funnyqueue.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @JoinColumn(name = "queue_id")
    @JsonBackReference
    private Queue queue;

    @ToString.Exclude
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "date_time", unique = true)
    private LocalDateTime dateTime;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
