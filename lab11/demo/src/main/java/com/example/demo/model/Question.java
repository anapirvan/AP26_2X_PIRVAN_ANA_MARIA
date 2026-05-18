package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 500)
    private String text;

    @Column(nullable = false)
    private String correctAnswer;

    @Column(nullable = false)
    private String wrongAnswers;

    public List<String> getWrongAnswersList() {
        return Arrays.asList(wrongAnswers.split(","));
    }

    @ManyToMany(mappedBy = "questions")
    private List<Game> games = new ArrayList<>();
}
