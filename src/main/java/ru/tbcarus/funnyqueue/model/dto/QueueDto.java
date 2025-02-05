package ru.tbcarus.funnyqueue.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueDto {
    private Integer id;
    private String name;
    private LocalTime WorkStartTime;
    private LocalTime WorkEndTime;
    Integer SlotTime; //в минутах
    private boolean enabled;
}
