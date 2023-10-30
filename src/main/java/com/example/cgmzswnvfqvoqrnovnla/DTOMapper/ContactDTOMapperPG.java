package com.example.cgmzswnvfqvoqrnovnla.DTOMapper;

import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactPgDTO;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ContactDTOMapperPG implements Function<ContactPG, ContactPgDTO> {
    @Override
    public ContactPgDTO apply(ContactPG contact){
         return new ContactPgDTO(contact.getId(),contact.getName(),contact.getBirthdate(),contact.getNumbers(),contact.getCreationDate());
    }
}
