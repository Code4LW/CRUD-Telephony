package com.example.cgmzswnvfqvoqrnovnla.Register;

import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryPG;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RegisterPostgres {

    @Autowired
    private ContactRepositoryPG contactRepositoryPG;


    @PostConstruct
    private void initDB(){
        if(contactRepositoryPG.count()==0){
        Set<String> numbers = new HashSet<>();
        numbers.add("+7777888999");
        numbers.add("+7444555666");
        Set<String> numbers2 = new HashSet<>();
        numbers2.add("+7111222333");
        numbers2.add("+7147147147");
        Set<String> numbers3 = new HashSet<>();
        numbers3.add("+7258258258");
        numbers3.add("+7369369369");
        List<ContactPG> contacts = new ArrayList<>();
        contacts.add(
                new ContactPG(1l, "John Smith", LocalDate.of(2000,10,18),numbers, LocalDate.now()));
        contacts.add(
                new ContactPG(2l, "Jane Doe", LocalDate.of(2001,01,30),numbers2, LocalDate.now()));
        contacts.add(new ContactPG(3l, "Sam Smith", LocalDate.of(2005,05,5),numbers3, LocalDate.now()));
        contactRepositoryPG.saveAll(contacts);}
    }
}
