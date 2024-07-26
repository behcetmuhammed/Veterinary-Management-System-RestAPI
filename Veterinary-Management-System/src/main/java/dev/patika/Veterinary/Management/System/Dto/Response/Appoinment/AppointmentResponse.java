package dev.patika.Veterinary.Management.System.Dto.Response.Appoinment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {

    private Long id;
    private LocalDateTime appointmentDate;
    private AppointmentAnimalResponse animal;
    private AppointmentDoctorResponse doctor;
}
