package com.dojang.dao;

import com.dojang.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ResultDao extends JpaRepository<Result, Integer> {

    @Query("SELECT r FROM Result r WHERE r.match.id = :matchId")
    Optional<Result> findByMatchId(@Param("matchId") Long matchId);

}
