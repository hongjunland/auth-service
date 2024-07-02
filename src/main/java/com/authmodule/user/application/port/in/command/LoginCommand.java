package com.authmodule.user.application.port.in.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record LoginCommand(@Email @NotBlank String email, @NotBlank String password) {
}
