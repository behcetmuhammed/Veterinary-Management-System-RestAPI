package dev.patika.Veterinary.Management.System.Dto.Request.AvailableDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateSaveRequest {

    @NonNull
    private LocalDate availableDate;

    @NonNull
    private AvailableDateDoctorRequest doctor_id; //Müsait Tarih Doktor Talebi (Doktorun müsati olduğu tarih)
}
