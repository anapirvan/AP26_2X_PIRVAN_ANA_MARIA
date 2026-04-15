package org.example.lab7.client;

public record MovieDTO(
        Integer id,
        String title,
        String releaseDate,
        int duration,
        int score,
        String genreName
) {}
