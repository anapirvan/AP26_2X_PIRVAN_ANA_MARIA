package org.example.lab7.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@Entity

@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private int duration;
    private int score;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

}
