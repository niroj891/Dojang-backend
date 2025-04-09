package com.dojang.dao;

import com.dojang.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoundDao extends JpaRepository<Round, Long> {

    // Count rounds by match ID
    @Query("SELECT COUNT(r) FROM Round r WHERE r.match.id = :matchId")
    int countByMatchId(@Param("matchId") Long matchId);

    // Find rounds by match event ID and weight category
    @Query("SELECT r FROM Round r WHERE r.match.event.id = :eventId AND r.match.weightCategory = :weightCategory ORDER BY r.match.roundNumber, r.roundNumber")
    List<Round> findByMatchEventIdAndMatchWeightCategory(
            @Param("eventId") Integer eventId,
            @Param("weightCategory") String weightCategory);

    // Find rounds by match ID
    @Query("SELECT r FROM Round r WHERE r.match.id = :matchId ORDER BY r.roundNumber")
    List<Round> findByMatchIdOrderByRoundNumber(@Param("matchId") Long matchId);

}