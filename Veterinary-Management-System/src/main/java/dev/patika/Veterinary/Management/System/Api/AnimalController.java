package dev.patika.Veterinary.Management.System.Api;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IAnimalService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dto.Request.Animal.AnimalSaveRequest;
import dev.patika.Veterinary.Management.System.Dto.Request.Animal.AnimalUpdateRequest;
import dev.patika.Veterinary.Management.System.Entities.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final IAnimalService animalService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addAnimal(@RequestBody AnimalSaveRequest animal) {
        return animalService.addAnimal(modelMapperService.forRequest().map(animal, Animal.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findAnimal(@PathVariable Long id) {
        return animalService.findAnimal(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAnimal(@PathVariable Long id) {
        return animalService.deleteAnimal(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateAnimal(@RequestBody AnimalUpdateRequest animal) {
        return animalService.updateAnimal(modelMapperService.forRequest().map(animal, Animal.class));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        return animalService.findByName(name);
    }

    @GetMapping("/findByCustomerId/{id}")
    public ResponseEntity findByCustomer_Id(@PathVariable Long id) {
        return animalService.findByCustomer_Id(id);
    }

    @GetMapping("/findAnimalByCustomerName/{name}")
    public ResponseEntity findByAnimalByCustomerName(@PathVariable String name) {
        return animalService.getAnimalByCustomerName(name);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        return animalService.findAll();
    }
}
