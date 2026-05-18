package com.example.demo.repository;

import com.example.demo.model.Player;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    @Query("SELECT p FROM Player p ORDER BY p.totalScore DESC")
    List<Player> findAllOrderByScore();

    @Modifying
    @Transactional
    @Query("UPDATE Player p SET p.totalScore = p.totalScore + :score WHERE p.name = :name")
    void updateScore(@Param("name") String name, @Param("score") int score);
}
