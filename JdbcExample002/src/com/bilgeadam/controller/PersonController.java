package com.bilgeadam.controller;

import com.bilgeadam.entity.Person;
import com.bilgeadam.service.PersonService;

public class PersonController {

    private final PersonService personService;

    public PersonController() {
       this.personService = new PersonService();
    }

    public void insertPerson(Person person){
        personService.insertPerson(person);
    }

    public void getAllPerson(){
        personService.getAllPerson();
    }
}
