package itmo.ru.infosec.dto;

import jakarta.validation.constraints.NotBlank;

public record DataDto(
        @NotBlank
        String message
) {
}