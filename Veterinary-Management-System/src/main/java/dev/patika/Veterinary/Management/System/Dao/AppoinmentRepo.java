package dev.patika.Veterinary.Management.System.Dao;

import dev.patika.Veterinary.Management.System.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppoinmentRepo extends JpaRepository<Appointment, Long> {
}
