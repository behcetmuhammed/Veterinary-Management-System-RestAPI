package dev.patika.Veterinary.Management.System.Business.Concretes;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IDoctorService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dao.DoctorRepo;
import dev.patika.Veterinary.Management.System.Dto.Response.Doctor.DoctorResponse;
import dev.patika.Veterinary.Management.System.Entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {


    private final DoctorRepo doctorRepo;
    private final ModelMapperService modelMapperService;


    //Doktor Ekle
    @Override
    public ResponseEntity addDoctor(Doctor doctor) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Doctor> hasDoctor = doctorRepo.findById(doctor.getId());
        try {
            if(doctorRepo.existsByMailOrPhone(doctor.getMail(),doctor.getPhone())){
                hashMap.put("Status", false);
                hashMap.put("Message", "Mevcut Kayıt!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            doctorRepo.save(doctor);
            hashMap.put("Status", true);
            hashMap.put("Message", "Doktor Oluşturuldu!");
            hashMap.put("Result", modelMapperService.forResponse().map(doctor, DoctorResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doktor Kaydedilemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Doktor Bul
    @Override
    public ResponseEntity findDoctor(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Doctor doctor = doctorRepo.findById(id).orElse(null);
        if(doctor != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(doctor, DoctorResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Doktor Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //Doktor Sil
    @Override
    public ResponseEntity deleteDoctor(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasDoctor = doctorRepo.existsById(id);
        try {
            if(hasDoctor) {
                doctorRepo.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Doktor silindi!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Doktor Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doktor silinemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Doktor Güncelle
    @Override
    public ResponseEntity updateDoctor(Doctor doctor) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctor.getId());
        try {
            if(optionalDoctor.isPresent()) {
                doctorRepo.saveAndFlush(doctor);
                hashMap.put("Status", true);
                hashMap.put("Message", "Doktora Güncellendi!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Doktor Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doktor güncellenemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    //Tüm Doktorı Getir (Listele)
    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Doctor> doctors = doctorRepo.findAll();
        List<DoctorResponse> converted = new ArrayList<>();
        try {
            if(doctors.isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Kayıt Bulunamadı!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            hashMap.put("Status", true);
            for(Doctor doctor : doctors){
                converted.add(modelMapperService.forResponse().map(doctor, DoctorResponse.class));
            }
            hashMap.put("Message", "Doktorlara gösterildi!");
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doktorlar yüklenemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }
}
