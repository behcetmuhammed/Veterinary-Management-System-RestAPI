package dev.patika.Veterinary.Management.System.Business.Abstruct;

import dev.patika.Veterinary.Management.System.Entities.AvailableDate;
import org.springframework.http.ResponseEntity;

public interface IAvailableDateService {

    //AvailableDate metodları

    public ResponseEntity addAvailableDate(AvailableDate availableDate);

    public ResponseEntity findAvailableDate(Long id);

    public ResponseEntity deleteAvailableDate(Long id);

    public ResponseEntity updateAvailableDate(AvailableDate availableDate);

    public ResponseEntity findAll();
}
