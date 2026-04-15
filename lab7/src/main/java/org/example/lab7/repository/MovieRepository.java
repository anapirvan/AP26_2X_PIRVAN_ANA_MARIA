package org.example.lab7.repository;

import org.example.lab7.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    boolean existsByTitleAndReleaseDate(String title, LocalDate releaseDate);
}
