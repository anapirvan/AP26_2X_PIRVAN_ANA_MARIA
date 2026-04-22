package org.example.lab7.dto;

public record MovieResponseDTO(
        Integer id,
        String title,
        String releaseDate,
        int duration,
        int score,
        String genreName
) {}
