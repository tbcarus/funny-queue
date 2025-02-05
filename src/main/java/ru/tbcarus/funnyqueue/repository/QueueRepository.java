package ru.tbcarus.funnyqueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbcarus.funnyqueue.model.Queue;

public interface QueueRepository extends JpaRepository<Queue, Integer> {

    Queue findQueueByUserId(Integer userId);

}
