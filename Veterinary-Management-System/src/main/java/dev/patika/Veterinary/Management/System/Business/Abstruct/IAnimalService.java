package dev.patika.Veterinary.Management.System.Business.Abstruct;

import dev.patika.Veterinary.Management.System.Entities.Animal;
import org.springframework.http.ResponseEntity;

public interface IAnimalService {
    //Hayvana ait metodlar

    public ResponseEntity addAnimal(Animal animal); //Hayvan ekle

    public ResponseEntity findAnimal(Long id); //Hayvan IDye göre bul

    public ResponseEntity deleteAnimal(Long id); //Hayvan sil

    public ResponseEntity updateAnimal(Animal animal); //Hayvan güncelle

    public ResponseEntity findByName(String name); //Hayvanı ismine göre getir

    public ResponseEntity findByCustomer_Id(Long id); //Hayvanı sahibinin IDsine göre getir

    public ResponseEntity getAnimalByCustomerName(String name); //Hayvanı sahibinin isimine göre getir

    public ResponseEntity findAll(); //Hayvanı tüm verilerini getir getir
}
