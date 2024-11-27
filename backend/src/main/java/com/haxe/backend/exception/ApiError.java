package com.haxe.backend.exception;

import java.time.ZonedDateTime;
import java.util.List;

public record ApiError(
        String path,
        String message,
        int code,
        ZonedDateTime time,
        List<String> errors) {

}
