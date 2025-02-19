package ru.tbcarus.funnyqueue.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EntityAlreadyExistException extends RuntimeException {
    private final String entityName;
    private final String message;
}
