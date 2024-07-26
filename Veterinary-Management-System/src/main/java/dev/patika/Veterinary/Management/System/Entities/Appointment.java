package dev.patika.Veterinary.Management.System.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private long id;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;


    //Appointment (x)<=>(1) Animal
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_animal_id", referencedColumnName = "animal_id", nullable = false)
    private Animal animal;


    //Appointment (x)<=>(1) Doctor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "doctor_id", nullable = false)
    private Doctor doctor;


    //Appointment (x)<=>(1) AvailableDate
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_available_date_id", referencedColumnName = "available_date_id", nullable = false)
    private AvailableDate availableDate;
}