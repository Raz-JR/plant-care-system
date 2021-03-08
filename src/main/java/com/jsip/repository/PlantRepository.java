package com.jsip.repository;

import com.jsip.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, String> {
    Optional<Plant> findByName(String name);
}
