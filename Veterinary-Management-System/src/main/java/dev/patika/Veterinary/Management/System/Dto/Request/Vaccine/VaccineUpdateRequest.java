package dev.patika.Veterinary.Management.System.Dto.Request.Vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineUpdateRequest {

    @NonNull
    private String name;
    @NonNull
    private String code;
    @NonNull
    private LocalDate protectionStartDate;
    @NonNull
    private LocalDate protectionFinishDate;
    @NonNull
    private VaccineAnimalRequest animal_id;
}
