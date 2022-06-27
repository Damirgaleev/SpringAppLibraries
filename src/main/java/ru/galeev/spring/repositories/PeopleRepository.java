package ru.galeev.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.galeev.spring.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
