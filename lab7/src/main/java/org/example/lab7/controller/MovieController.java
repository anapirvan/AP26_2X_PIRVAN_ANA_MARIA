package org.example.lab7.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.lab7.dto.MovieRequestDTO;
import org.example.lab7.dto.ScoreRequestDTO;
import org.example.lab7.model.Movie;
import org.example.lab7.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Tag(name="Movie Controller")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(summary = "Obtain all movies")
    @ApiResponse(responseCode = "200", description = "Movies obtained successfully")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @Operation(summary = "Create a new movie")
    @ApiResponse(responseCode = "201", description = "Movie created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "404", description = "Genre not found")
    @ApiResponse(responseCode = "409", description = "Unique constraint (title, releaseDate) violated")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping
    public Movie create(@Valid @RequestBody MovieRequestDTO request){
        return movieService.save(request);
    }

    @Operation(summary = "Update a movie")
    @ApiResponse(responseCode = "200", description = "Movie updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "404", description = "Movie/Genre not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id, @Valid @RequestBody MovieRequestDTO request){
        return movieService.update(id,request);
    }

    @Operation(summary = "Update the score of a movie")
    @ApiResponse(responseCode = "200", description = "Movie updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "404", description = "Movie/Genre not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PatchMapping("/{id}/score")
    public Movie updateScore(@PathVariable int id, @Valid @RequestBody ScoreRequestDTO request){
        return movieService.updateScore(id, request.score());
    }

    @Operation(summary = "Delete a movie")
    @ApiResponse(responseCode = "200", description = "Movie deleted successfully")
    @ApiResponse(responseCode = "404", description = "Movie/Genre not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        movieService.delete(id);
    }
}
