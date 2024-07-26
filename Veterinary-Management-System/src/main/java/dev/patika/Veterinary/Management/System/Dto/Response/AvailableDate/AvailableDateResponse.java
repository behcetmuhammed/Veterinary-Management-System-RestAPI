package dev.patika.Veterinary.Management.System.Dto.Response.AvailableDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateResponse {

    private long id;
    private LocalDate availableDate;
    private AvailableDateDoctorResponse doctor;
}
