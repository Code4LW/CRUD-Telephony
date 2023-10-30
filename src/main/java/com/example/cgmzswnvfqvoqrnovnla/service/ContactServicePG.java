package com.example.cgmzswnvfqvoqrnovnla.service;

import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactPgDTO;
import com.example.cgmzswnvfqvoqrnovnla.DTOMapper.ContactDTOMapperPG;
import com.example.cgmzswnvfqvoqrnovnla.FIlter.Filter;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryPG;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class ContactServicePG {
    private final ContactRepositoryPG contactRepositoryPG;
    private final ContactDTOMapperPG contactDTOMapperPG;

    public void createContact(ContactPG contact){
        contactRepositoryPG.save(contact);
    }

    public void updateContact(Long id, ContactPG contact){
        ContactPG new_contact = contactRepositoryPG.getById(id);
        new_contact.setName(contact.getName());
        new_contact.setBirthdate(contact.getBirthdate());

        new_contact.setCreationDate(contact.getCreationDate());
        contactRepositoryPG.save(new_contact);
    }
    public void updateContact(String phoneNumber, ContactPG contact){
        ContactPG new_contact = contactRepositoryPG.findByNumbers(phoneNumber);
        new_contact.setName(contact.getName());
        new_contact.setBirthdate(contact.getBirthdate());
        new_contact.setNumbers(contact.getNumbers());
        new_contact.setCreationDate(contact.getCreationDate());
        contactRepositoryPG.save(new_contact);
    }

    public List<ContactPgDTO> getContacts(){
         return contactRepositoryPG.findAll().stream().map(contactDTOMapperPG).collect(Collectors.toList());

    }
    public Page<ContactPgDTO> getContacts(Filter filter){
        int offset = (filter.getOffset()-1) * filter.getLimit();
        if(filter.getOffset() == 1) {
            filter.setOffset(0);
            offset = filter.getOffset() * filter.getLimit();
        }

        Page<ContactPG> page = contactRepositoryPG.findAll(PageRequest.of(offset, filter.getLimit()));
        return page.map(contactDTOMapperPG);

    }
    public ContactPgDTO getById(Long id){
        return contactDTOMapperPG.apply(
                contactRepositoryPG.findById(id).orElse(null)
        );
    }
    public ContactPgDTO getByPhoneNumber(String phoneNumber){
        List<ContactPgDTO> filtered = contactRepositoryPG.findAll().stream().filter(
                contact -> contact.getNumbers().contains(phoneNumber)).map(contactDTOMapperPG).collect(Collectors.toList());
        return filtered.isEmpty()? null:filtered.get(0);

    }


    public void deleteAll(){
        contactRepositoryPG.deleteAll();
    }

    public void deleteById(Long id){
        contactRepositoryPG.deleteById(id);
    }
    public void deleteByPhoneNumber(String phoneNumber){
        List<ContactPG> list = contactRepositoryPG.findAll().stream().filter(contactPG -> contactPG.getNumbers().contains(phoneNumber)).collect(Collectors.toList());
        if(!list.isEmpty()) contactRepositoryPG.deleteById(list.get(0).getId());
    }

}
