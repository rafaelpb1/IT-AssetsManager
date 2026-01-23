package com.rafael.itmanager.exception;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime  timestamp,
        int status,
        String erro,
        String messagem
) {
}
