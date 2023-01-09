package com.example.lab2.repo;

import com.example.lab2.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepo extends JpaRepository<PositionEntity,Integer> {
}
