package dev.patika.Veterinary.Management.System.Dao;

import dev.patika.Veterinary.Management.System.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DoctorRepo  extends JpaRepository<Doctor, Long> {

    @Query("""
            select d from Doctor d inner join d.availableDates availableDates
            where d.id = ?1 and availableDates.availableDate = ?2""")
    Doctor findByIdAndAvailableDates_AvailableDate(long id, LocalDate availableDate);

    @Query("""
            select d from Doctor d inner join d.appointmentList appointmentList
            where d.id = ?1 and appointmentList.appointmentDate = ?2""")
    Doctor findByIdAndAppointmentList_AppointmentDate(long id, LocalDateTime appointmentDate);

    boolean existsByMailOrPhone(String mail, String phone);
}
