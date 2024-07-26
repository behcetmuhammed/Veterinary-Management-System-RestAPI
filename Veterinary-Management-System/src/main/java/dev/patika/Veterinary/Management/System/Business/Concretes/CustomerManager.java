package dev.patika.Veterinary.Management.System.Business.Concretes;

import dev.patika.Veterinary.Management.System.Business.Abstruct.ICustomerService;
import dev.patika.Veterinary.Management.System.Core.Config.ModalMapper.ModelMapperService;
import dev.patika.Veterinary.Management.System.Dao.CustomerRepo;
import dev.patika.Veterinary.Management.System.Dto.Response.Customer.CustomerResponse;
import dev.patika.Veterinary.Management.System.Entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerManager implements ICustomerService {


    private final CustomerRepo customerRepo;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addCustomer(Customer customer) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Customer> hasCustomer = customerRepo.findById(customer.getId());
        try {
            if(hasCustomer.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Mevcut Kayıt! Bu kayıt zaten var!");
                hashMap.put("Customer ID", customer.getId());
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            customerRepo.save(customer);
            hashMap.put("Status", true);
            hashMap.put("Message", "Müşteri Başarıyla Kaydedildi!");
            hashMap.put("Result", modelMapperService.forResponse().map(customer, CustomerResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Müşteri kaydedilemedi!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findCustomer(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Customer customer = customerRepo.findById(id).orElse(null);
        if(customer != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(customer, CustomerResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Customer Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteCustomer(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasCustomer = customerRepo.existsById(id);
        try {
            if(hasCustomer) {
                customerRepo.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Customer has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Customer could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateCustomer(Customer customer) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Customer> optionalCustomer = customerRepo.findById(customer.getId());
        try {
            if(optionalCustomer.isPresent()) {
                customerRepo.saveAndFlush(customer);
                hashMap.put("Status", true);
                hashMap.put("Message", "Customer has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Customer could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findCustomerByName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Customer> customerList = customerRepo.findByName(name);
        List<CustomerResponse> converted = new ArrayList<>();
        if(!customerList.isEmpty()) {
            hashMap.put("Status", true);
            for(Customer customer : customerList) {
                converted.add(modelMapperService.forResponse().map(customer, CustomerResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Customer(s) Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Customer> customers = customerRepo.findAll();
        List<CustomerResponse> converted = new ArrayList<>();
        if(!customers.isEmpty()) {
            hashMap.put("Status", true);
            for(Customer customer : customers){
                converted.add(modelMapperService.forResponse().map(customer, CustomerResponse.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Customer(s) Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public Customer getId(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity getByAnimalList(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if(customerRepo.findById(id).isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        if(getId(id).getAnimals().isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        hashMap.put("Result", getId(id).getAnimals());
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
