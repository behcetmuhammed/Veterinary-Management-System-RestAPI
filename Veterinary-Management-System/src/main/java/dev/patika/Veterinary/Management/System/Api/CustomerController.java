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
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addCustomer(@RequestBody CustomerSaveRequest customer) {
        return customerService.addCustomer(modelMapperService.forRequest().map(customer, Customer.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody CustomerUpdateRequest customer) {
        return customerService.updateCustomer(modelMapperService.forRequest().map(customer, Customer.class));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity findCustomerByName(@PathVariable String name) {
        return customerService.findCustomerByName(name);
    }

    @GetMapping("/byAnimalList/{id}")
    public ResponseEntity getByAnimalList(@PathVariable("id") Long id) {
        return customerService.getByAnimalList(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        return customerService.findAll();
    }
}
