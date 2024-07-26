package dev.patika.Veterinary.Management.System.Dto.Request.Appoinment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {

    @NonNull
    private Long id;

    @NonNull
    private LocalDateTime appointmentDate;

    @NonNull
    private AppointmentAnimalRequest animal_id;

    @NonNull
    private AppointmentDoctorRequest doctor_id;

    @NonNull
    private AppointmentAvailableDateRequest available_date_id;
}
