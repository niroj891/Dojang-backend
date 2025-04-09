package com.dojang.dao;

import com.dojang.model.Match;
import com.dojang.model.MatchStatus;
import com.dojang.model.WeightCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatchDao extends JpaRepository<Match, Long> {

    // Find matches by event ID and weight category
    @Query("SELECT m FROM Match m WHERE m.event.id = :eventId AND m.weightCategory = :weightCategory ORDER BY m.roundNumber")
    List<Match> findByEventIdAndWeightCategoryOrderByRoundNumber(
            @Param("eventId") Integer eventId,
            @Param("weightCategory") WeightCategory weightCategory);

    // Find the maximum round number for an event and weight category
    @Query("SELECT MAX(m.roundNumber) FROM Match m WHERE m.event.id = :eventId AND m.weightCategory = :weightCategory")
    Optional<Integer> findMaxRoundByEventAndWeightCategory(
            @Param("eventId") Integer eventId,
            @Param("weightCategory") WeightCategory weightCategory);

    // Find matches by status
    @Query("SELECT m FROM Match m WHERE m.status = :status")
    List<Match> findByStatus(MatchStatus status);

    // Delete matches by event ID and weight category
    @Modifying
    @Query("DELETE FROM Match m WHERE m.event.id = :eventId AND m.weightCategory = :weightCategory")
    void deleteByEventIdAndWeightCategory(
            @Param("eventId") Integer eventId,
            @Param("weightCategory") WeightCategory weightCategory);


}