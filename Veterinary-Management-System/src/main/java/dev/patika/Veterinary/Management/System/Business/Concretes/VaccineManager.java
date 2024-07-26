package dev.patika.Veterinary.Management.System.Business.Concretes;


import dev.patika.Veterinary.Management.System.Business.Abstruct.IVaccineService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dao.AnimalRepo;
import dev.patika.Veterinary.Management.System.Dao.VaccineRepo;
import dev.patika.Veterinary.Management.System.Dto.Response.Vaccine.VaccineResponse;
import dev.patika.Veterinary.Management.System.Entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addVaccine(Vaccine vaccine) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> existingVaccines = vaccineRepo.findByNameAndCode(vaccine.getName(), vaccine.getCode());
        Optional<Vaccine> hasVaccine = vaccineRepo.findById(vaccine.getId());
        try {
            // Belirtilen hayvanın depoda mevcut olup olmadığını kontrol etme...
            if (animalRepo.findById(vaccine.getAnimal().getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Hayvan Bulunamadı!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            // Aynı kod, isim ve hayvan kimliğine sahip bir aşının halihazırda mevcut olup olmadığını kontrol etme..
            if (vaccineRepo.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
                // Belirtilen başlangıç tarihinden sonra bitiş tarihi olan herhangi bir aşı olup olmadığını kontrol etme
                if(vaccineRepo.findByProtectionFinishDateAfterOrderByProtectionFinishDate(vaccine.getProtectionStartDate()).isEmpty()){
                    //  bitiş tarihi başlangıç tarihinden önce ise
                    if (ChronoUnit.DAYS.between(vaccine.getProtectionStartDate(), vaccine.getProtectionFinishDate()) < 0) {
                        hashMap.put("Status", false);
                        hashMap.put("Message",  "Anlamsız İstisna - Geçersiz Tarih!");
                        return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
                    }
                    // Tüm kontroller geçerse aşıyı kaydet..
                    vaccineRepo.save(vaccine);
                    hashMap.put("Status", true);
                    hashMap.put("Message", "New vaccine added successfully.");
                    hashMap.put("Result", modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
                    return new ResponseEntity<>(hashMap, HttpStatus.OK);
                }
                // Throw an exception if there's a date mismatch
                hashMap.put("Status", false);
                hashMap.put("Message", "Tarih Uyuşmazlığı!");
                return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
            }
            // Check if the end date is before the start date
            if (ChronoUnit.DAYS.between(vaccine.getProtectionStartDate(), vaccine.getProtectionFinishDate()) < 0) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Anlamsız İstisna - Geçersiz Tarih!");
                return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
            }
            // Save the vaccine if all checks pass
            vaccineRepo.save(vaccine);
            hashMap.put("Status", true);
            hashMap.put("Message", "Yeni aşı başarıyla eklendi.");
            hashMap.put("Result", modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.OK);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Aşı kaydedilemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findVaccine(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Vaccine vaccine = vaccineRepo.findById(id).orElse(null);
        if(vaccine != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Aşı Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteVaccine(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasVaccine = vaccineRepo.existsById(id);
        try {
            if(hasVaccine) {
                vaccineRepo.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Aşı silindi!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Aşı Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Aşı silinemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Vaccine getId(Long id) {
        return vaccineRepo.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity updateVaccine(Vaccine vaccine) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Vaccine> optionalVaccine = vaccineRepo.findById(vaccine.getId());
        try {
            // Aşının varlığını IDyle doğrulayın
            getId(vaccine.getId());
            // Aynı kod, isim ve hayvan kimliğine sahip bir aşının halihazırda mevcut olup olmadığını kontrol etme
            if (vaccineRepo.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
                // Belirtilen başlangıç tarihinden sonra bitiş tarihi olan herhangi bir aşı olup olmadığını kontrol etme
                if(vaccineRepo.findByProtectionFinishDateAfterOrderByProtectionFinishDate(vaccine.getProtectionStartDate()).isEmpty()){
                    // Bitiş tarihinin başlangıç tarihinden önce olup olmadığını kontrol etme
                    if (ChronoUnit.DAYS.between(vaccine.getProtectionStartDate(), vaccine.getProtectionFinishDate()) < 0) {
                        hashMap.put("Status", false);
                        hashMap.put("Message", "Anlamsız İstisna - Geçersiz Tarih!");
                        return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
                    }
                    // Tüm kontroller geçrse güncellenen aşıyı kaydet
                    hashMap.put("Status", true);
                    hashMap.put("Message", "Vaccine has been updated!");
                    vaccineRepo.saveAndFlush(vaccine);
                    return new ResponseEntity<>(hashMap, HttpStatus.OK);
                }
            }
            // Aşı yoksa "yeni aşı" olarak kaydet
            return addVaccine(vaccine);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Aşı güncellenemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findByAnimal_Id(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccineList = vaccineRepo.findByAnimal_Id(id);
        List<VaccineResponse> converted = new ArrayList<>();
        if(vaccineList != null) {
            hashMap.put("Status", true);
            for(Vaccine vaccine : vaccineList) {
                converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Kayıt Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findALl() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccines = vaccineRepo.findAll();
        List<VaccineResponse> converted = new ArrayList<>();
        try{
            if(vaccines.isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Kayıt Bulunamadı!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            hashMap.put("Status", true);
            for(Vaccine vaccine : vaccines) {
                converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
            }
            hashMap.put("Message", "Aşılar gösteriliyor!");
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doktorlar yüklenemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    // Filter by Animal Vaccines
    @Override
    public ResponseEntity getAnimalVaccineList(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccines = vaccineRepo.findByAnimal_Id(id);
        List<VaccineResponse> converted = new ArrayList<>();
        if (vaccineRepo.findByAnimal_Id(id).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Aşı Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Vaccine vaccine : vaccines) {
            converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
        }
        hashMap.put("Message", "Aşılar gösteriliyor!");
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }

    // Filter By Protection End Date
    @Override
    public ResponseEntity getFilterByStartAndEndDate(LocalDate startDate, LocalDate endDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccines = vaccineRepo.findByProtectionFinishDateBetween(startDate,endDate);
        List<VaccineResponse> converted = new ArrayList<>();
        if (vaccineRepo.findByProtectionFinishDateBetween(startDate,endDate).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Aşı Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Vaccine vaccine : vaccines) {
            converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
        }
        hashMap.put("Message", "Aşılar gösteriliyor!");
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
