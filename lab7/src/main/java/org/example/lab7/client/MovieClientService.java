package org.example.lab7.client;

import org.example.lab7.dto.MovieRequestDTO;
import org.example.lab7.dto.MovieResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class MovieClientService {
    private final RestClient restClient;

    public MovieClientService() {
        this.restClient = RestClient.builder().baseUrl("http://localhost:8081/api/movies").build();
    }

    public MovieResponseDTO addMovie(MovieRequestDTO dto) {
        return restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(MovieResponseDTO.class);
    }

    public void updateMovie(int id, MovieRequestDTO dto) {
        restClient.put()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }

    public MovieResponseDTO patchScore(int id, int newScore) {
        return restClient.patch()
                .uri("/{id}/score", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("score", newScore))
                .retrieve()
                .body(MovieResponseDTO.class);
    }

    public void deleteMovie(int id) {
        restClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
