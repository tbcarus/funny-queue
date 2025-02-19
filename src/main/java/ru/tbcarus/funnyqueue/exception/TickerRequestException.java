package ru.tbcarus.funnyqueue.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TickerRequestException extends RuntimeException {
    private final String message;
}
