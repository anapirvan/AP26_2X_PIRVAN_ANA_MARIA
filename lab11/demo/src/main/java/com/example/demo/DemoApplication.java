package com.example.demo;

import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerRepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Player player1 = new Player();
        player1.setName("Alice");
        player1.setTotalScore(50);
        playerRepository.save(player1);

        Player player2 = new Player();
        player2.setName("Bob");
        player2.setTotalScore(20);
        playerRepository.save(player2);

        gameService.getTopPlayers();

        gameService.updateScore("Bob", 40);
        gameService.getTopPlayers();
    }
}
