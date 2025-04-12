package com.dojang.dao;

import com.dojang.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResultDao extends JpaRepository<Result, Integer> {
}
