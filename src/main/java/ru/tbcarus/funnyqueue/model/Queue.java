package ru.tbcarus.funnyqueue.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "queues")
public class Queue extends AbstractBaseEntity {

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 128, message = "Длина имени от 2 до 128")
    private String name;

    @ToString.Exclude
    @OneToOne()
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "queue", cascade = CascadeType.ALL)
    List<Slot> slots;

    @Column(name = "work_start_time")
    private LocalTime WorkStartTime;

    @Column(name = "work_end_time")
    private LocalTime WorkEndTime;

    @Column(name = "lunch_start_time")
    private LocalTime LunchStartTime;

    @Column(name = "queue_start_time")
    private LocalTime QueueStartTime;

    @Column(name = "queue_end_time")
    private LocalTime QueueEndTime;

    @Column(name = "slot_time")
    Integer SlotTime; //в минутах

    private boolean enabled;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public boolean isQueueAvailable() {
        return !WorkStartTime.equals(WorkEndTime)
                && WorkStartTime.isBefore(QueueStartTime) || WorkEndTime.isAfter(QueueEndTime);
    }
}
