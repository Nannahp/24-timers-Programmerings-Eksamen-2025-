package com.example.prg2025.repository;

import com.example.prg2025.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query("SELECT d FROM Delivery d WHERE d.actualArrival IS NULL")
    List<Delivery> findAllNonDelivered();


    @Query("SELECT d FROM Delivery d WHERE d.drone IS NULL")
    List<Delivery>  findAllDeliveriesWithoutDrones();

}
