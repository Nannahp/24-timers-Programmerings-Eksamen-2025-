package com.example.prg2025.repository;

import com.example.prg2025.models.Drone;
import com.example.prg2025.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("SELECT d FROM Drone d WHERE d.status = :status")
    List<Drone> findDronesWithStatus(@Param("status") Status status);

}
