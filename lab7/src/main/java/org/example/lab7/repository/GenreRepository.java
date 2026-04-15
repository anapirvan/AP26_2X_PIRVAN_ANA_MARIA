package org.example.lab7.repository;

import jakarta.validation.constraints.NotBlank;
import org.example.lab7.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
}
