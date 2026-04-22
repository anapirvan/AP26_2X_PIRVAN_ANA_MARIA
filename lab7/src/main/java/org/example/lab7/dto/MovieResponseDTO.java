package org.example.lab7.dto;

import lombok.AllArgsConstructor;


public record MovieResponseDTO(
        Integer id,
        String title,
        String releaseDate,
        int duration,
        int score,
        String genreName
) {}
