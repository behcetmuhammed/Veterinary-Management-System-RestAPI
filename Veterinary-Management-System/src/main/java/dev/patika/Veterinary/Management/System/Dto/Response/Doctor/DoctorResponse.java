package dev.patika.Veterinary.Management.System.Dto.Response.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
