package dev.patika.Veterinary.Management.System.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "animals")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal { //Hayvan Sınıfı
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id; //Id

    @Column(name = "animal_name", length = 100, nullable = false)
    private String name; //Adı

    @Column(name = "animal_specie", nullable = false)
    private String species; //Tür

    @Column(name = "animal_breed", nullable = false)
    private String breed; //Cins

    @Column(name = "animal_color", nullable = false)
    private String color; //Renk

    @Column(name = "animal_birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth; //Doğum Tarihi


    //Daha sonra ENUM olarak ayarla
//    @Column(name = "animal_gender", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//    public enum Gender {
//        MALE, FEMALE
//    }
    @Column(name = "animal_gender", nullable = false)
    private String gender;

    //Animal (x)<=>(1) Customer  //Bir hayvanın sadece bir müşterisi olur, ancak müşterinin birden fazla hayvanı olabilir.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    //Animal (1)<=>(x) Vaccine  //Bir hayvan'a birden fazla kez aşı vurulabilir, ancak aşı hayvan'a sadece bir kez vurulur.
    // Hayvanın aşıları, Vaccine sınıfıyla ilişki (Lazy Fetch, Kaldırma işlemi)
    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccine;

    //Animal (1)<=>(x) Appointment
    // Hayvanın randevuları, Appointment sınıfıyla ilişki (Lazy Fetch, Kaldırma işlemi)
    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointment;
}


