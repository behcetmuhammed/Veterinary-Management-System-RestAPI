package dev.patika.Veterinary.Management.System.Dto.Request.Vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineExpiringRequest {

    private LocalDate startDate;
    private LocalDate endDate;
}
