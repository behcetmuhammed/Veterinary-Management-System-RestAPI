package dev.patika.Veterinary.Management.System.Api;

import dev.patika.Veterinary.Management.System.Business.Abstruct.ICustomerService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dto.Request.Customer.CustomerSaveRequest;
import dev.patika.Veterinary.Management.System.Dto.Request.Customer.CustomerUpdateRequest;
import dev.patika.Veterinary.Management.System.Entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers") //Müşteri (Hayvan sahibi)
@RequiredArgsConstructor
public class CustomerController { // Hayvan sahiplerini kaydetme, bilgilerini güncelleme, görüntüleme ve silme

    private final ICustomerService customerService;
    private final ModelMapperService modelMapperService;

    //Hayvan sahiplerini kaydetme
    @PostMapping("/save")
    public ResponseEntity addCustomer(@RequestBody CustomerSaveRequest customer) {
        return customerService.addCustomer(modelMapperService.forRequest().map(customer, Customer.class));
    }

    //Müşteriyi ID'ye göre bul
    @GetMapping("/findById/{id}")
    public ResponseEntity findCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    //Hayvan sahiplerini ID'ye göre silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    //Hayvan sahiplerini güncelleme
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody CustomerUpdateRequest customer) {
        return customerService.updateCustomer(modelMapperService.forRequest().map(customer, Customer.class));
    }

    //Hayvan sahiplerini isme göre getir
    @GetMapping("/findByName/{name}") //Hayvan sahipleri isme göre filtrelenecek şekilde end point oluşturmak.
    public ResponseEntity findCustomerByName(@PathVariable String name) {
        return customerService.findCustomerByName(name);
    }

    //Hayvan sahiplerinin, hayvanlarını ID'ye göre listele
    @GetMapping("/byAnimalList/{id}")
    public ResponseEntity getByAnimalList(@PathVariable("id") Long id) {
        return customerService.getByAnimalList(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        return customerService.findAll();
    }
}
