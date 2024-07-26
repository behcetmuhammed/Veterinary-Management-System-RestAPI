package dev.patika.Veterinary.Management.System.Business.Concretes;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IAppointmentService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dao.AnimalRepo;
import dev.patika.Veterinary.Management.System.Dao.AppointmentRepo;
import dev.patika.Veterinary.Management.System.Dao.AvailableDateRepo;
import dev.patika.Veterinary.Management.System.Dao.DoctorRepo;
import dev.patika.Veterinary.Management.System.Dto.Response.Appoinment.AppointmentResponse;
import dev.patika.Veterinary.Management.System.Entities.Animal;
import dev.patika.Veterinary.Management.System.Entities.Appointment;
import dev.patika.Veterinary.Management.System.Entities.AvailableDate;
import dev.patika.Veterinary.Management.System.Entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final ModelMapperService modelMapperService;
    private final DoctorRepo doctorRepo;
    private final AvailableDateRepo availableDateRepo;
    private final AnimalRepo animalRepo;


    //Randevu Ekle
    @Override
    public ResponseEntity addAppointment(Appointment appointment) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Appointment> hasAppointment = appointmentRepo.findById(appointment.getId());
        try {
            // Belirtilen doktorun ve hayvanın mevcut olup olmadığını kontrol etme...
            if (!doctorRepo.existsById(appointment.getDoctor().getId()) || !animalRepo.existsById(appointment.getAnimal().getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "ID Bulunamadı!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            // Belirtilen tarihte doktorun müsait olup olmadığını kontrol etme..
            if(availableDateRepo.findByAvailableDateIdInEndDateAndDoctorId(appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId()) == null){
                hashMap.put("Status", false);
                hashMap.put("Message", "Doktor belirtilen tarihte müsait değil!");
                return new ResponseEntity<>(hashMap, HttpStatus.CONFLICT);
            }
            // Randevu tarihi ve doktor için uygun tarih IDsini bulma
            Long availableId = availableDateRepo.findByAvailableDateIdInEndDateAndDoctorId(appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId());

            // Belirtilen tarih ve doktor için uygun tarihin mevcut olup olmadığını kontrol et..
            if (availableDateRepo.existsByIdAndAvailableDateAndDoctor_Id(availableId, appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId())) {
                // Randevu çakışmalarını kontrol et ve denetle
                for (int i = 0; i < appointmentRepo.findAll().size(); i++) {
                    if (appointmentRepo.existsByDoctor_Id(appointment.getDoctor().getId())) {
                        if (Duration.between(appointment.getAppointmentDate(), appointmentRepo.findAll().get(i).getAppointmentDate()).toHours() == 0) {
                            hashMap.put("Status", false);
                            hashMap.put("Message", "Randevu Çakışması!");
                            return new ResponseEntity<>(hashMap, HttpStatus.CONFLICT);
                        }
                    }
                }
                //Randevuyu uygun tarihle ilişkilendir ve kaydet
                AvailableDate availableDate = availableDateRepo.findById(availableId).orElseThrow();
                appointment.setAvailableDate(availableDate);

                hashMap.put("Status", true);
                hashMap.put("Message", "Randevu Oluşturuldu!");
                AppointmentResponse result = modelMapperService.forResponse().map(appointmentRepo.save(appointment), AppointmentResponse.class);
                hashMap.put("Result", result);
                return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
            }
            //Uygun bir tarih bulunamazsa, bir çakışma mesajı gönder (göster)
            hashMap.put("Status", false);
            hashMap.put("Message", "Uygun Tarih Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.CONFLICT);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Randevu oluşturulamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Randevu Bul (getir)
    @Override
    public ResponseEntity findAppointment(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        if(appointment != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(appointment, AppointmentResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Randevu Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //Randevu sil
    @Override
    public ResponseEntity deleteAppointment(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasAppointment = appointmentRepo.existsById(id);
        try {
            if(hasAppointment) {
                appointmentRepo.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Randevu silindi!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Randevu Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Randevu silinemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Randevu Güncelle
    @Override
    public ResponseEntity updateAppointment(Appointment appointment) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(appointment.getId());
        try {
            if(optionalAppointment.isPresent()) {
                appointmentRepo.saveAndFlush(appointment);
                hashMap.put("Status", true);
                hashMap.put("Message", "Randevu güncellendi!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Randevu Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Randevu güncellenemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Tarihe Göre Randevuları Bul
    @Override
    public ResponseEntity getAppointmentsByDateBetween(LocalDate startDate, LocalDate endDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointmentList = appointmentRepo.findByAppointmentDateBetween(startDate, endDate);
        List<AppointmentResponse> converted = new ArrayList<>();
        if(appointmentList != null){
            hashMap.put("Status", true);
            for(Appointment appointment : appointmentList){
                converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "İlgili Doktor ve Randevu Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //Tüm Randevuları bul (getir)
    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointments = appointmentRepo.findAll();
        List<AppointmentResponse> converted = new ArrayList<>();
        if(!appointments.isEmpty()) {
            hashMap.put("Status", true);
            for(Appointment appointment : appointments){
                converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Randevu Bulunamadı!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //Tarih, Saat ve Doktor göre filtrele
    @Override
    public ResponseEntity filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointments = appointmentRepo.findByAppointmentDateBetweenAndDoctor(startDate, endDate, doctor);
        List<AppointmentResponse> converted = new ArrayList<>();
        if (doctorRepo.findById(doctor.getId()).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Doktor Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        if (!appointmentRepo.existsByAppointmentDateBetween(startDate, endDate)) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Randevu Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Appointment appointment : appointments) {
            converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }


    //Tarih, Saat ve Hayvana göre filtrele
    @Override
    public ResponseEntity filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointments = appointmentRepo.findByAppointmentDateBetweenAndAnimal(startDate, endDate, animal);
        List<AppointmentResponse> converted = new ArrayList<>();
        if (animalRepo.findById(animal.getId()).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Hayvan Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        if (!appointmentRepo.existsByAppointmentDateBetween(startDate, endDate)) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Randevu Bulunamadı!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Appointment appointment : appointments) {
            converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
