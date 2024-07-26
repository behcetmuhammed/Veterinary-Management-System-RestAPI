package dev.patika.Veterinary.Management.System.Dao;

import dev.patika.Veterinary.Management.System.Entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepo  extends JpaRepository<Vaccine, Long> {
}
