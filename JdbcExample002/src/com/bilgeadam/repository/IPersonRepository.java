package com.bilgeadam.repository;

import com.bilgeadam.entity.Person;

public interface IPersonRepository {

    public void insertPerson(Person person);
    public void getAllPerson();
    public void deleteById(int id);
}
