package com.example.cgmzswnvfqvoqrnovnla.controller;

import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.service.ContactServicePG;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ContactConrollerPG {

    private final ContactServicePG contactService;

    @GetMapping("/all")
    public List<ContactPG> getAll() {
        return contactService.getContacts();

    }

    @GetMapping("/id/{id}")
    public ContactPG getContactById(@PathVariable Long id) {
        return contactService.getById(id);
    }
    @GetMapping("/number/{phoneNumber}")
    public ContactPG getContactById(@PathVariable String phoneNumber) {
        return contactService.getByPhoneNumber(phoneNumber);
    }

    @PostMapping("/create")
    public void saveContact(@RequestBody ContactPG contact) {
        ContactPG cont = contactService.getContacts().stream().filter(contactPG -> contactPG.getNumbers().equals(contact.getNumbers())).collect(Collectors.toList()).get(0);
        if(contactService.getContacts().contains(cont)) throw new RuntimeException("The contuct with similar number already exist!");

    }
    @PutMapping("/update/id/{id}")
        public void updateContactById(@PathVariable Long id, @RequestBody ContactPG contact){
            contactService.updateContact(id, contact);
        }
    @PutMapping("/update/number/{phoneNumber}")
    public void updateContactById(@PathVariable String phoneNumber, @RequestBody ContactPG contact){
        contactService.updateContact(phoneNumber, contact);
    }


    @DeleteMapping("/delete/id/{id}")
    public void deleteContactById(@PathVariable Long id) {
        contactService.deleteById(id);
    }

    @DeleteMapping("/delete/number/{phonenumber}")
    public void deleteContactByPhoneNumber(@PathVariable String phonenumber) {
        contactService.deleteByPhoneNumber(phonenumber);
    }

}
