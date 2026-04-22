package org.example.lab7.client;

import org.example.lab7.dto.MovieRequestDTO;
import org.example.lab7.dto.MovieResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientRunner {
    @Bean
    CommandLineRunner testApi(MovieClientService client) {
        return args -> {
            System.out.println("1. Se adaugă filmul");
            MovieRequestDTO newMovie = new MovieRequestDTO( "Inception", "2011-07-16", 148, 8, "Action");
            MovieResponseDTO saved = client.addMovie(newMovie);
            Integer id = saved.id();
            System.out.println("Salvat cu ID: " + id);


            System.out.println("\n2. Se actualizeaza filmul (PUT)");
            MovieRequestDTO updateData = new MovieRequestDTO( "Inception", "2010-07-16", 155, 8, "Action");
            client.updateMovie(id, updateData);
            System.out.println("Update realizat");


            System.out.println("\n3. Se modifica doar scorul (PATCH)...");
            MovieResponseDTO patched = client.patchScore(id, 9);
            System.out.println("Scor nou: " + patched.score());


            System.out.println("\n4. Se șterge filmul...");
            client.deleteMovie(id);
            System.out.println("Film șters cu succes.");
        };
    }
}
