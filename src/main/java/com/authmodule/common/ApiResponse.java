package com.authmodule.common;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
public abstract class ApiResponse {
    protected int status;
    protected String message;
}
