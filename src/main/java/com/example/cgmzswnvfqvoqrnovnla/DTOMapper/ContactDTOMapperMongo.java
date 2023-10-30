package com.example.cgmzswnvfqvoqrnovnla.DTOMapper;

import com.example.cgmzswnvfqvoqrnovnla.DTO.ContactMongoDTO;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactMongo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ContactDTOMapperMongo implements Function<ContactMongo, ContactMongoDTO> {
    @Override
    public ContactMongoDTO apply(ContactMongo contactMongo){
        return new ContactMongoDTO(
                contactMongo.getId(),contactMongo.getName(),contactMongo.getBirthdate(),contactMongo.getNumbers(),contactMongo.getCreationDate()
        );
    }
}
