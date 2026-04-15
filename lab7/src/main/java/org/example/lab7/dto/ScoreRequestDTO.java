package org.example.lab7.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ScoreRequestDTO(

        @Min(value = 1, message = "Scorul trebuie să fie între 1 și 10")
        @Max(value = 10, message = "Scorul trebuie să fie între 1 și 10")
        int score
) {}
