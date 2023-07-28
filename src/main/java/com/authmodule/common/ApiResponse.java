package com.authmodule.common;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
public abstract class ApiResponse {
    protected final int status;
    protected final String message;
}

