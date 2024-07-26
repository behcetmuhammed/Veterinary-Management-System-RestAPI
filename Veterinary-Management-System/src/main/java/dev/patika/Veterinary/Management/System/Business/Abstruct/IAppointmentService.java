package dev.patika.Veterinary.Management.System.Business.Abstruct;

import dev.patika.Veterinary.Management.System.Entities.Animal;
import dev.patika.Veterinary.Management.System.Entities.Appointment;
import dev.patika.Veterinary.Management.System.Entities.Doctor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IAppointmentService {
    //Randevuya ait metodlar

    public ResponseEntity addAppointment(Appointment appointment);

    public ResponseEntity findAppointment(Long id);

    public ResponseEntity deleteAppointment(Long id);

    public ResponseEntity updateAppointment(Appointment appointment);

    public ResponseEntity getAppointmentsByDateBetween(LocalDate startDate, LocalDate endDate);

    public ResponseEntity findAll();

    public ResponseEntity filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);
    public ResponseEntity filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);
}
