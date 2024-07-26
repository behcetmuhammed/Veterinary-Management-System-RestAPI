package dev.patika.Veterinary.Management.System.Api;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IVaccineService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dto.Request.Vaccine.VaccineSaveRequest;
import dev.patika.Veterinary.Management.System.Dto.Request.Vaccine.VaccineUpdateRequest;
import dev.patika.Veterinary.Management.System.Entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final IVaccineService vaccineService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addVaccine(@RequestBody VaccineSaveRequest vaccine) {
        return vaccineService.addVaccine(modelMapperService.forRequest().map(vaccine, Vaccine.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findVaccine(@PathVariable Long id) {
        return vaccineService.findVaccine(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVaccine(@PathVariable Long id) {
        return vaccineService.deleteVaccine(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateVaccine(@RequestBody VaccineUpdateRequest vaccine) {
        return vaccineService.updateVaccine(modelMapperService.forRequest().map(vaccine, Vaccine.class));
    }

    @GetMapping("/findByAnimal/{id}")
    public ResponseEntity findByAnimal_Id(@PathVariable Long id) {return vaccineService.findByAnimal_Id(id);}

    @GetMapping("/findAll")
    public ResponseEntity findAll() {return vaccineService.findALl();}

    @GetMapping("/expiring")
    public ResponseEntity getFilterByStartAndEndDate(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return vaccineService.getFilterByStartAndEndDate(startDate, endDate);
    }
}
