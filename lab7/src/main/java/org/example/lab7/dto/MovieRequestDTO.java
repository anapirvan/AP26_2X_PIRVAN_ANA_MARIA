package org.example.lab7.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

public record MovieRequestDTO(

        @NotBlank(message = "Titlul este obligatoriu")
        String title,

        @NotBlank(message = "Data lansării este obligatorie")
        String releaseDate,

        @Min(value = 1, message = "Durata trebuie să fie mai mare decât 0")
        int duration,

        @Min(value = 1, message = "Scorul trebuie să fie între 1 și 10")
        @Max(value = 10, message = "Scorul trebuie să fie între 1 și 10")
        int score,

        @NotBlank(message = "Genul este obligatoriu")
        String genreName

) {}