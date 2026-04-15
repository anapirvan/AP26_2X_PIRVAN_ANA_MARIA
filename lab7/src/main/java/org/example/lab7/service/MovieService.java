package org.example.lab7.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab7.dto.MovieRequestDTO;
import org.example.lab7.model.Genre;
import org.example.lab7.model.Movie;
import org.example.lab7.repository.GenreRepository;
import org.example.lab7.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie save(MovieRequestDTO request){
        LocalDate date = LocalDate.parse(request.releaseDate());
        if (movieRepository.existsByTitleAndReleaseDate(request.title(), date)){
            throw new IllegalArgumentException("Filmul '" + request.title() + "' din data de " + date + " exista deja!");
        }

        Movie movie=new Movie();
        movie.setTitle(request.title());
        movie.setReleaseDate(LocalDate.parse(request.releaseDate()));
        movie.setDuration(request.duration());
        movie.setScore(request.score());

        Genre genre= genreRepository.findByName(request.genreName()).orElseThrow(()->new EntityNotFoundException("Genre not found"));
        movie.setGenre(genre);

        return movieRepository.save(movie);
    }

    public Movie update(int id, MovieRequestDTO request){
        Movie movie=movieRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Nu exista niciun film cu acest id!"));

        movie.setReleaseDate(LocalDate.parse(request.releaseDate()));
        movie.setDuration(request.duration());
        movie.setScore(request.score());

        Genre genre= genreRepository.findByName(request.genreName()).orElseThrow(()->new EntityNotFoundException("Genre not found"));
        movie.setGenre(genre);

        return movieRepository.save(movie);
    }

    public Movie updateScore(int id, int score){
        Movie movie=movieRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Nu exista nciun film cu acest id!"));

        movie.setScore(score);
        return movieRepository.save(movie);
    }

    public void delete(int id){
        if(!movieRepository.existsById(id)){
            throw new EntityNotFoundException("Nu exista nixiun film cu acest id!");
        }
        movieRepository.deleteById(id);
    }
}
