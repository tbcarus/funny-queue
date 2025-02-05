package ru.tbcarus.funnyqueue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbcarus.funnyqueue.model.Break;
import ru.tbcarus.funnyqueue.model.Queue;
import ru.tbcarus.funnyqueue.model.Role;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.repository.QueueRepository;

import java.time.LocalTime;
import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;

    @Transactional
    public Queue getQueue(User user) {
        Queue queue = queueRepository.findQueueByUserId(user.getId());
        if (queue == null && user.getRoles().contains(Role.SUPERUSER)) {
            queue = createQueueIfNotExists(user);
        }
        return queue;
    }

    @Modifying
    @Transactional
    protected Queue createQueueIfNotExists(User user) {
        Queue queue = Queue.builder()
                .name(user.getName() + " - queue")
                .user(user)
                .WorkStartTime(LocalTime.of(9, 0))
                .WorkEndTime(LocalTime.of(18, 0))
                .SlotTime(30)
                .enabled(true)
                .build();

        Break lunchBreak = Break.builder()
                .name("Обед")
                .queue(queue)
                .startTime(LocalTime.of(13, 0))
                .endTime(LocalTime.of(14, 30))
                .build();

        queue.setBreaks(List.of(lunchBreak));

        return queueRepository.save(queue);
    }
}
