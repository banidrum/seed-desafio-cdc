package com.dev.eficiente.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthorDto {
    @NotBlank
    String name;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(max = 400)
    String description;
}
