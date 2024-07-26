package dev.patika.Veterinary.Management.System.Business.Concretes;

import dev.patika.Veterinary.Management.System.Business.Abstruct.IAnimalService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dao.AnimalRepo;
import dev.patika.Veterinary.Management.System.Dto.Response.Animal.AnimalResponse;
import dev.patika.Veterinary.Management.System.Entities.Animal;
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
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final ModelMapperService modelMapperService;


    //Hayvan ekle
    @Override
    public ResponseEntity addAnimal(Animal animal) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Animal hasAnimal = animalRepo.findByNameAndSpeciesAndBreedAndGenderAndColorAndDateOfBirth(animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getGender(), animal.getColor(), animal.getDateOfBirth());
        try {
            if(hasAnimal != null) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            AnimalResponse result = modelMapperService.forResponse().map(animalRepo.save(animal), AnimalResponse.class);
            hashMap.put("Status", true);
            hashMap.put("Message", "Record Created!");
            hashMap.put("Result", result);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be saved!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Hayvan IDye göre bul
    @Override
    public ResponseEntity findAnimal(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Animal animal = animalRepo.findById(id).orElse(null);
        if(animal != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(animal, AnimalResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //Hayvanı IDye göre sil
    @Override
    public ResponseEntity deleteAnimal(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasAnimal = animalRepo.existsById(id);
        try {
            if(hasAnimal) {
                animalRepo.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Hayvanı güncelle
    @Override
    public ResponseEntity updateAnimal(Animal animal) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Animal> optionalAnimal = animalRepo.findById(animal.getId());
        try {
            if(optionalAnimal.isPresent()) {
                animalRepo.saveAndFlush(animal);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }


    //Hayvanı ismine göre getir
    @Override
    public ResponseEntity findByName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animalList = animalRepo.findByName(name);
        List<AnimalResponse> converted = new ArrayList<>();
        if(animalList != null) {
            hashMap.put("Status", true);
            for(Animal animal : animalList){
                converted.add(modelMapperService.forResponse().map(animal, AnimalResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //Hayvanı sahibinin IDsine göre getir
    @Override
    public ResponseEntity findByCustomer_Id(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animalList = animalRepo.findByCustomer_Id(id);
        List<AnimalResponse> converted = new ArrayList<>();
        if(animalList != null) {
            hashMap.put("Status", true);
            for(Animal animal : animalList){
                converted.add(modelMapperService.forResponse().map(animal, AnimalResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }


    //Hayvanı sahibinin isimine göre getir
    @Override
    public ResponseEntity getAnimalByCustomerName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animalList = animalRepo.findByCustomerName(name);
        List<AnimalResponse> converted = new ArrayList<>();
        if(animalList.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Animal animal : animalList){
            converted.add(modelMapperService.forResponse().map(animal, AnimalResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }


    //Hayvanı tüm verilerini getir getir
    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animals = animalRepo.findAll();
        List<AnimalResponse> converted = new ArrayList<>();
        if(animals.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Animal animal : animals){
            converted.add( modelMapperService.forResponse().map(animal, AnimalResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
