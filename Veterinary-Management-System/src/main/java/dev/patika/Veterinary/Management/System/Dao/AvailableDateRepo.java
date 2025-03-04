package dev.patika.Veterinary.Management.System.Dao;

import dev.patika.Veterinary.Management.System.Entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    boolean existsByAvailableDateAndDoctor_Id(LocalDate date, Long id);
    boolean existsByIdAndAvailableDateAndDoctor_Id(Long dateId, LocalDate date, Long id);

    @Query("select a.id from AvailableDate a where a.availableDate = :endDate and a.doctor.id = :doctorId")
    Long findByAvailableDateIdInEndDateAndDoctorId(@Param("endDate") LocalDate endDate, @Param("doctorId") Long doctorId);
}
