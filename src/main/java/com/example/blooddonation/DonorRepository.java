package com.example.blooddonation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    @Query("SELECT d FROM Donor d WHERE (:bloodGroup IS NULL OR d.bloodGroup = :bloodGroup) AND (:location IS NULL OR d.location = :location)")
    List<Donor> search(@Param("bloodGroup") String bloodGroup, @Param("location") String location);
}
