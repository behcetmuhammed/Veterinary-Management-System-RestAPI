package dev.patika.Veterinary.Management.System.Dao;

import dev.patika.Veterinary.Management.System.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo  extends JpaRepository<Doctor, Long> {
}
