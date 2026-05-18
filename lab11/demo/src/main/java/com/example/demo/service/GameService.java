package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);
    private PlayerRepository playerRepository;

    public GameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getTopPlayers() {
        long start = System.currentTimeMillis();
        try {
            List<Player> players = playerRepository.findAllOrderByScore();
            long duration = System.currentTimeMillis() - start;
            logger.info("getTopPlayers ea durat {} ms, {} jucatori gasiti", duration, players.size());

            players.forEach(p -> logger.info("Player: {} - Score: {}", p.getName(), p.getTotalScore()));

            return players;
        } catch (Exception exception) {
            logger.error("Eroare la getTopPlayers: {}", exception.getMessage());
            throw exception;
        }
    }

    public void updateScore(String name, int score) {
        long start = System.currentTimeMillis();
        try {
            playerRepository.updateScore(name, score);
            long duration = System.currentTimeMillis() - start;
            logger.info("updateScore pentru {} cu {} puncte, a durat {} ms", name, score, duration);
        } catch (Exception exception) {
            logger.error("Eroare la updateScore pentru {}: {}", name, exception.getMessage());
            throw exception;
        }
    }
}