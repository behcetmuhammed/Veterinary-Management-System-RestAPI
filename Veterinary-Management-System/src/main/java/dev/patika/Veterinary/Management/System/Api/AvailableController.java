package dev.patika.Veterinary.Management.System.Api;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IAvailableDateService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dto.Request.AvailableDate.AvailableDateSaveRequest;
import dev.patika.Veterinary.Management.System.Dto.Request.AvailableDate.AvailableDateUpdateRequest;
import dev.patika.Veterinary.Management.System.Entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/available-dates")
@RequiredArgsConstructor
public class AvailableController {


    private final IAvailableDateService availableDateService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addAvailableDate(@RequestBody AvailableDateSaveRequest availableDate) {
        return availableDateService.addAvailableDate(modelMapperService.forRequest().map(availableDate, AvailableDate.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findAvailableDate(@PathVariable Long id) {
        return availableDateService.findAvailableDate(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAvailableDate(@PathVariable Long id) {
        return availableDateService.deleteAvailableDate(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateAvailableDate(@RequestBody AvailableDateUpdateRequest availableDate) {
        return availableDateService.updateAvailableDate(modelMapperService.forRequest().map(availableDate, AvailableDate.class));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        return availableDateService.findAll();
    }
}
