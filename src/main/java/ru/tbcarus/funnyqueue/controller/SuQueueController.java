package ru.tbcarus.funnyqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.funnyqueue.model.Queue;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.service.QueueService;

@RestController
@RequestMapping("/su/queue")
public class SuQueueController {

    @Autowired
    QueueService queueService;

    @GetMapping
    public Queue getQUeue(@AuthenticationPrincipal User user) {
        return queueService.getQueue(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateQueue(@AuthenticationPrincipal User user, @RequestBody Queue queue) {

    }

//    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Queue updateQueue(@AuthenticationPrincipal User user, @RequestBody Queue queue) {
//        queueService.update(queue);
//    }
//
//    @DeleteMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteQueue(@AuthenticationPrincipal User user) {
//        queueService.delete(user);
//    }
//
//    @PatchMapping("/enable")
//    public void reverseEnableQueue(@AuthenticationPrincipal User user) {
//        queueService.reverseEnableQueue(user);
//    }
}
