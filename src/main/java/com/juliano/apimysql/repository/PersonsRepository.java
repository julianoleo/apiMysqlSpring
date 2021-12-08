package com.juliano.apimysql.repository;

import com.juliano.apimysql.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Long> {
}