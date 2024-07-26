package dev.patika.Veterinary.Management.System.Business.Abstruct;

import dev.patika.Veterinary.Management.System.Entities.Doctor;
import org.springframework.http.ResponseEntity;

public interface IDoctorService {

    //Doktora ait metodlar
    public ResponseEntity addDoctor(Doctor doctor);

    public ResponseEntity findDoctor(Long id);

    public ResponseEntity deleteDoctor(Long id);

    public ResponseEntity updateDoctor(Doctor doctor);

    public ResponseEntity findAll();
}
