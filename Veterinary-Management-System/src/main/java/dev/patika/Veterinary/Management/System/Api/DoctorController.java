package dev.patika.Veterinary.Management.System.Api;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IDoctorService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dto.Request.Doctor.DoctorSaveRequest;
import dev.patika.Veterinary.Management.System.Dto.Request.Doctor.DoctorUpdateRequest;
import dev.patika.Veterinary.Management.System.Entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {


    private final IDoctorService doctorService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addDoctor(@RequestBody DoctorSaveRequest doctor) {
        return doctorService.addDoctor(modelMapperService.forRequest().map(doctor, Doctor.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findDoctor(@PathVariable Long id) {
        return doctorService.findDoctor(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        return doctorService.deleteDoctor(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateDoctor(@RequestBody DoctorUpdateRequest doctor) {
        return doctorService.updateDoctor(modelMapperService.forRequest().map(doctor, Doctor.class));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAllDoctors() {
        return doctorService.findAll();
    }

}
