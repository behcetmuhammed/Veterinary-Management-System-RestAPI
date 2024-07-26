package dev.patika.Veterinary.Management.System.Dto.Response.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
