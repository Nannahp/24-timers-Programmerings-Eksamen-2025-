package com.example.prg2025.repository;

import com.example.prg2025.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s ORDER BY SIZE(s.drones) ASC")
    List<Station> findStationWithLeastDrones();

}
