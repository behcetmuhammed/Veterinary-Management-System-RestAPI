package dev.patika.Veterinary.Management.System.Api;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IAppointmentService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dao.AnimalRepo;
import dev.patika.Veterinary.Management.System.Dao.DoctorRepo;
import dev.patika.Veterinary.Management.System.Dto.Request.Appoinment.AppointmentSaveRequest;
import dev.patika.Veterinary.Management.System.Dto.Request.Appoinment.AppointmentUpdateRequest;
import dev.patika.Veterinary.Management.System.Entities.Animal;
import dev.patika.Veterinary.Management.System.Entities.Appointment;
import dev.patika.Veterinary.Management.System.Entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppoinmentController {

    private final IAppointmentService appointmentService;
    private final ModelMapperService modelMapperService;
    private final DoctorRepo doctorRepo;
    private final AnimalRepo animalRepo;

    @PostMapping("/save")
    public ResponseEntity addAppointment(@RequestBody AppointmentSaveRequest appointment) {
        return appointmentService.addAppointment(modelMapperService.forRequest().map(appointment, Appointment.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findAppointment(@PathVariable Long id) {
        return appointmentService.findAppointment(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateAppointment(@RequestBody AppointmentUpdateRequest appointment) {
        return appointmentService.updateAppointment(modelMapperService.forRequest().map(appointment, Appointment.class));
    }

    @GetMapping("/dateDoctor")
    public ResponseEntity filterDateTimeAndDoctor(@RequestParam("startDate") LocalDateTime startDate,
                                                  @RequestParam("endDate") LocalDateTime endDate,
                                                  @RequestParam("doctorId") Long doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);
        return appointmentService.filterDateTimeAndDoctor(startDate, endDate, doctor);
    }


    @GetMapping("/dateAnimal")
    public ResponseEntity filterDateTimeAndAnimal(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                  @RequestParam("animalId") Long animalId) {
        Animal animal = animalRepo.findById(animalId).orElse(null);
        return appointmentService.filterDateTimeAndAnimal(startDate, endDate, animal);
    }

    @GetMapping("/findAll")
    public ResponseEntity findALL() {
        return appointmentService.findAll();
    }
}
