package com.authmodule.common;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public abstract class ApiResponse implements Serializable {
    protected final int status;
    protected final String message;
}

