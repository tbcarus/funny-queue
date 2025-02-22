package ru.tbcarus.funnyqueue.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @OneToMany( mappedBy = "queue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Slot> slots;

    @OneToMany(mappedBy = "queue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Break> breaks;

    @Column(name = "work_start_time")
    private LocalTime WorkStartTime;

    @Column(name = "work_end_time")
    private LocalTime WorkEndTime;

    @Column(name = "slot_time")
    Integer SlotTime; //в минутах

    private boolean enabled;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public boolean isQueueAvailable() {
        // придумать что-нибудь для определения доступности. Что-нибудь вроде что есть свободное время для слотов,
        // например, всё рабочее время не забито перерывами.
        return true;
    }
}
