package dev.patika.Veterinary.Management.System.Dao;

import dev.patika.Veterinary.Management.System.Entities.Animal;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AnimalRepo extends JpaRepository<Animal, Long> { //Animal'ın ID'si veri tipi Long

/*
    List<Animal> findByName(String name);
    List<Animal> findByCustomer_Id(Long id);
    List<Animal> findByCustomerName(String name);
    Animal findByNameAndSpeciesAndBreedAndGenderAndColorAndDateOfBirth(
            String name,
            String species,
            String breed,
            String gender,
            String color,
            LocalDate dateOfBirth);*/
    /*dwdw

     */
}
