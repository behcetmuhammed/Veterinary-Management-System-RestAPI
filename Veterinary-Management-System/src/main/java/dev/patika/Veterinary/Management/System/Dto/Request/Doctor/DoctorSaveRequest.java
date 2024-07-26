package dev.patika.Veterinary.Management.System.Dto.Request.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSaveRequest {

    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String phone;
    @NonNull
    private String mail;
    @NonNull
    private String address;
    @NonNull
    private String city;
}
