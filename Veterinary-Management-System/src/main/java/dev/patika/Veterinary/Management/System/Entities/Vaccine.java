package dev.patika.Veterinary.Management.System.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "vaccines")
@Getter
@Setter
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private long id;

    @Column(name = "vaccine_name", nullable = false)
    private String name;

    @Column(name = "vaccine_code", nullable = false)
    private String code;

    @Column(name = "vaccine_protection_start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionStartDate;

    @Column(name = "vaccine_protection_end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionFinishDate;


    //Veccine (x)<=>(1) Animal
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id", nullable = false)
    private Animal animal;
}