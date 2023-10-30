package com.example.cgmzswnvfqvoqrnovnla.controller;

import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactPgDTO;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.service.ContactServicePG;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ContactConrollerPG {

    private final ContactServicePG contactService;

    @GetMapping("/all")
    public List<ContactPgDTO> getAll() {
        return contactService.getContacts();

    }

    @GetMapping("/id/{id}")
    public ContactPgDTO getContactById(@PathVariable Long id) {
        return contactService.getById(id);
    }
    @GetMapping("/number/{phoneNumber}")
    public ContactPgDTO getContactById(@PathVariable String phoneNumber) {
        return contactService.getByPhoneNumber(phoneNumber);
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveContact(@RequestBody ContactPG contact) {
            try {
                contactService.createContact(contact);
                return ResponseEntity.ok().body("The contact is created and saved");
            }catch (RuntimeException exception){
                return ResponseEntity.internalServerError().body("The number is already used!");// на случай если у создаваемого контакта есть уже использованный номер
            }

    }
    @PutMapping("/update/id/{id}")
        public ResponseEntity<String> updateContactById(@PathVariable Long id, @RequestBody ContactPG contact){
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
