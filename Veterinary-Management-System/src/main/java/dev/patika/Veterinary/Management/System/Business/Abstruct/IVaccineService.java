package dev.patika.Veterinary.Management.System.Business.Abstruct;

import dev.patika.Veterinary.Management.System.Entities.Vaccine;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface IVaccineService {

    //Aşı'ya ait metodlar

    public ResponseEntity addVaccine(Vaccine vaccine);

    public ResponseEntity findVaccine(Long id);

    public ResponseEntity deleteVaccine(Long id);

    public ResponseEntity updateVaccine(Vaccine vaccine);

    public ResponseEntity findByAnimal_Id(Long id);

    public ResponseEntity findALl();

    Vaccine getId(Long id);

    ResponseEntity getAnimalVaccineList(Long id);

    ResponseEntity getFilterByStartAndEndDate(LocalDate startDate, LocalDate endDate);
}
