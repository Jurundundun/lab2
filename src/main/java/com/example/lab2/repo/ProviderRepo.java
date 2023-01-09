package com.example.lab2.repo;

import com.example.lab2.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepo extends JpaRepository<ProviderEntity,Integer> {
}
