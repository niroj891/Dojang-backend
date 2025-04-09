package com.dojang.dao;

import com.dojang.model.Participation;
import com.dojang.model.PlayerStatus;
import com.dojang.model.WeightCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ParticipationDao extends JpaRepository<Participation, Integer> {

    // Find participants by event ID
    @Query("SELECT p FROM Participation p WHERE p.event.id = :eventId")
    List<Participation> findByEventId(Integer eventId);

    // Find participants by event ID and weight category
    @Query("SELECT p FROM Participation p WHERE p.event.id = :eventId AND p.weightCategory = :weightCategory")
    List<Participation> findByEventIdAndWeightCategory(Integer eventId, WeightCategory weightCategory);

    // Find active participants by event ID, weight category and status
    @Query("SELECT p FROM Participation p WHERE p.event.id = :eventId AND p.weightCategory = :weightCategory AND p.playerStatus = :status")
    List<Participation> findByEventIdAndWeightCategoryAndPlayerStatus(
            Integer eventId,
            WeightCategory weightCategory,
            PlayerStatus status);
    // Update participant status
    @Modifying
    @Transactional
    @Query("UPDATE Participation p SET p.playerStatus = :status WHERE p.id = :id")
    void updateParticipantStatus(Long id, PlayerStatus status);

    // Reset all participants status for an event and weight category
    @Modifying
    @Transactional
    @Query("UPDATE Participation p SET p.playerStatus = 'NOTOUT' WHERE p.event.id = :eventId AND p.weightCategory = :weightCategory")
    void resetAllParticipantsStatus(Integer eventId, WeightCategory weightCategory);
}