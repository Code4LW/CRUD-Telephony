package com.example.cgmzswnvfqvoqrnovnla.service;

import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactMongoDTO;
import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactPgDTO;
import com.example.cgmzswnvfqvoqrnovnla.DTOMapper.ContactDTOMapperMongo;
import com.example.cgmzswnvfqvoqrnovnla.FIlter.Filter;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactMongo;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContactServiceMongo {
    private final ContactRepositoryMongo contactRepositoryMongo;
    private final ContactDTOMapperMongo contactDTOMapperMongo;

    public void createContact(ContactMongo contact){
        contactRepositoryMongo.save(contact);
    }

    public void updateContact(Long id, ContactMongo contact){
        ContactMongo new_contact = contactRepositoryMongo.getById(id);
        new_contact.setName(contact.getName());
        new_contact.setBirthdate(contact.getBirthdate());
        new_contact.setNumbers(contact.getNumbers());
        new_contact.setCreationDate(contact.getCreationDate());
        contactRepositoryMongo.save(new_contact);
    }
    public void updateContact(String phoneNumber, ContactPG contact){
        ContactMongo new_contact = contactRepositoryMongo.findByNumbers(phoneNumber);
        new_contact.setName(contact.getName());
        new_contact.setBirthdate(contact.getBirthdate());
        new_contact.setNumbers(contact.getNumbers());
        new_contact.setCreationDate(contact.getCreationDate());
        contactRepositoryMongo.save(new_contact);
    }
    public List<ContactMongoDTO> getContacts(){
        return contactRepositoryMongo.findAll().stream().map(contactDTOMapperMongo).collect(Collectors.toList());
    }
    public Page<ContactMongoDTO> getContacts(Filter filter){
        int offset = (filter.getOffset()-1) * filter.getLimit();
        if(filter.getOffset() == 1) {
            filter.setOffset(0);
            offset = filter.getOffset() * filter.getLimit();
        }
        Page<ContactMongo> page = contactRepositoryMongo.findAll(PageRequest.of(offset, filter.getLimit()));
        return page.map(contactDTOMapperMongo);
    }
    public ContactMongoDTO getById(Long id){
        return contactDTOMapperMongo.apply(
                contactRepositoryMongo.findById(id).orElse(null)
        );
    }
    public ContactMongoDTO getByPhoneNumber(String phoneNumber){
        List<ContactMongoDTO> filtered = contactRepositoryMongo.findAll().stream().filter(
                contact -> contact.getNumbers().contains(phoneNumber)).map(contactDTOMapperMongo).collect(Collectors.toList());
        return filtered.isEmpty()? null:filtered.get(0);
    }
    public void deleteAll(){
        contactRepositoryMongo.deleteAll();
    }
    public void deleteById(Long id){
        contactRepositoryMongo.deleteById(id);
    }
    public void deleteByPhoneNumber(String phoneNumber){
        List<ContactMongo> list = contactRepositoryMongo.findAll().stream().filter(contactPG -> contactPG.getNumbers().contains(phoneNumber)).collect(Collectors.toList());
        if(!list.isEmpty()) contactRepositoryMongo.deleteById(list.get(0).getId());
    }



}
