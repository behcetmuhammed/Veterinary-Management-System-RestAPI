package dev.patika.Veterinary.Management.System.Business.Abstruct;

import dev.patika.Veterinary.Management.System.Entities.Customer;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {
    //Hayvan sahibine (Müşteriye) ait metodlar

    public ResponseEntity addCustomer(Customer customer); //Müşteri ekle

    public ResponseEntity findCustomer(Long id); //Müşteriyi IDye göre bul

    public ResponseEntity deleteCustomer(Long id); //Müşteriyi sil

    public ResponseEntity updateCustomer(Customer customer); //Müşteriyi güncelle

    public ResponseEntity findCustomerByName(String name); //Müşteriyi ismine göre bul

    public ResponseEntity findAll(); //Müşterinin tüm verilerini getir

    Customer getId(Long id);

    ResponseEntity getByAnimalList(Long id); //Müşteriye ait hayvaları listele
}
