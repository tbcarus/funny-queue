package ru.tbcarus.funnyqueue.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenRevokedException extends RuntimeException {
    private final String token;
    private final String message;
}
