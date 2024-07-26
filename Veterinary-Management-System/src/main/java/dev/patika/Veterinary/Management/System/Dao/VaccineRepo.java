package dev.patika.Veterinary.Management.System.Dao;

import dev.patika.Veterinary.Management.System.Entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo  extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimal_Id(long id);
    List<Vaccine> findByNameAndCode(String name, String code);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate firstDate, LocalDate endDate);
    List<Vaccine> findByProtectionFinishDateAfterOrderByProtectionFinishDate(LocalDate endDate);
    Boolean existsVaccineByCodeAndNameAndAnimal_id(String code, String name, long id);
}
