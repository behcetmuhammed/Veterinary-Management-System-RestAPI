package dev.patika.Veterinary.Management.System.Dto.Response.Animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private int id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String color;
    private LocalDate dateOfBirth;
    private AnimalCustomerResponse customer;
}
