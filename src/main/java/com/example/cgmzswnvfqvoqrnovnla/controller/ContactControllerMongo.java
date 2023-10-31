package com.example.cgmzswnvfqvoqrnovnla.controller;

import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactMongoDTO;
import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactPgDTO;
import com.example.cgmzswnvfqvoqrnovnla.FIlter.Filter;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactMongo;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.service.ContactServiceMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Mongo")
public class ContactControllerMongo {
    private final ContactServiceMongo contactService;

    @GetMapping("/all")
    public List<ContactMongoDTO> getAll() {
        return contactService.getContacts();
    }
    @GetMapping("/all/paged/{limit}/{offset}")
    public ResponseEntity<List<ContactMongoDTO>> getAllPaged(@PathVariable int limit, @PathVariable int offset){
        Filter filter = new Filter(limit, offset);
        Page<ContactMongoDTO> allPaged = contactService.getContacts(filter);
        return new ResponseEntity(allPaged.getContent(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ContactMongoDTO getContactById(@PathVariable Long id) {
        return contactService.getById(id);
    }
    @GetMapping("/number/{phoneNumber}")
    public ContactMongoDTO getContactById(@PathVariable String phoneNumber) {
        return contactService.getByPhoneNumber(phoneNumber);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addContact(@RequestBody ContactMongo contact) {
            List<String> numbers = new ArrayList<>();
            //создааем лист, и добавляем туда все номера всех контактов, так как они все уникальные
            contactService.getContacts().forEach(
                    contactMongoDTO -> contactMongoDTO.getNumbers().forEach(numbers::add));
            //проверяем содержит ли номера заданного контакта уже сохраненные номера и в таком случае отправляем сообщение
            for(String number: numbers){
                if(contact.getNumbers().contains(number)) return ResponseEntity.internalServerError().body("The number is already used!");
            }
            contactService.createContact(contact);
            return ResponseEntity.ok().body("The contact is created and saved");

        }
    @PutMapping("/update/id/{id}")
    public ResponseEntity<String> updateContactById(@PathVariable Long id, @RequestBody ContactMongo contact){
        contactService.updateContact(id, contact);
        return ResponseEntity.ok().body("The contact is updated");
    }
    @PutMapping("/update/number/{phoneNumber}")
    public ResponseEntity<String> updateContactById(@PathVariable String phoneNumber, @RequestBody ContactPG contact){
        contactService.updateContact(phoneNumber, contact);
        return ResponseEntity.ok().body("The contact is updated");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll(){
        contactService.deleteAll();
        return ResponseEntity.ok().body("ALl entities are deleted");
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.ok().body("The contact is deleted");
    }

    @DeleteMapping("/delete/number/{phonenumber}")
    public ResponseEntity<String> deleteContactByPhoneNumber(@PathVariable String phonenumber) {
        contactService.deleteByPhoneNumber(phonenumber);
        return ResponseEntity.ok("The contact is deleted");
    }
}
