package dev.patika.Veterinary.Management.System.Business.Concretes;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IAvailableDateService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dao.AppoinmentRepo;
import dev.patika.Veterinary.Management.System.Dao.AvailableDateRepo;
import dev.patika.Veterinary.Management.System.Dao.DoctorRepo;
import dev.patika.Veterinary.Management.System.Dto.Response.AvailableDate.AvailableDateResponse;
import dev.patika.Veterinary.Management.System.Entities.AvailableDate;
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
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;
    private final AppoinmentRepo appointmentRepo;
    private final ModelMapperService modelMapperService;


    //Uygun Tarih Ekle
    @Override
    public ResponseEntity addAvailableDate(AvailableDate availableDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            if (doctorRepo.findById(availableDate.getDoctor().getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Doktor ID'si Bulunamadı!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            if (availableDateRepo.existsByAvailableDateAndDoctor_Id(availableDate.getAvailableDate(), availableDate.getDoctor().getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Mevcut Kayıt!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            availableDateRepo.save(availableDate);
            hashMap.put("Status", true);
            hashMap.put("Message", "Uygun Tarih Oluşturuldu!");
            hashMap.put("Result", modelMapperService.forResponse().map(availableDate, AvailableDateResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Uygun Tarih kaydedilemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //ID'ye göre Uygun Tarih bul
    @Override
    public ResponseEntity findAvailableDate(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        AvailableDate availableDate = availableDateRepo.findById(id).orElse(null);
        if(availableDate != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(availableDate, AvailableDateResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Uygun Tarih Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //ID'ye göre Uygun Tarih sil
    @Override
    public ResponseEntity deleteAvailableDate(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasAvailableDate = availableDateRepo.existsById(id);
        try {
            if(hasAvailableDate) {
                availableDateRepo.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Uygun Tarih silindi!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Uygun Tarih Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Uygun Tarih silinemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Uygun Tarih güncelle
    @Override
    public ResponseEntity updateAvailableDate(AvailableDate availableDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<AvailableDate> optionalAvailableDate = availableDateRepo.findById(availableDate.getId());
        try {
            if (appointmentRepo.existsByAvailableDate_Id(availableDate.getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Mevcut Randevu!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            //Check if the specified available date exists in the repository
            if(availableDateRepo.findById(availableDate.getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Mevcut Tarih ID'si Bulunamadı!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            //Check if the specified doctor exists in the repository
            if (doctorRepo.findById(availableDate.getDoctor().getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Doktor Bulunamadı!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            // Check if the same date and doctor combination already exists in the repository
            if (availableDateRepo.existsByAvailableDateAndDoctor_Id(availableDate.getAvailableDate(), availableDate.getDoctor().getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Mevcut Kayıt!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            //Save the updated available date to the repository
            availableDateRepo.saveAndFlush(availableDate);
            hashMap.put("Status", true);
            hashMap.put("Message", "Uygun Tarih güncellendi!");
            return new ResponseEntity<>(hashMap, HttpStatus.OK);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Uygun Tarih güncellenemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Uygun Tarihlerin hepsini getir
    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<AvailableDate> availableDates = availableDateRepo.findAll();
        List<AvailableDateResponse> converted = new ArrayList<>();
        if(!availableDates.isEmpty()) {
            hashMap.put("Status", true);
            for(AvailableDate availableDate : availableDates) {
                converted.add(modelMapperService.forResponse().map(availableDate, AvailableDateResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Uygun Tarih Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }
}
