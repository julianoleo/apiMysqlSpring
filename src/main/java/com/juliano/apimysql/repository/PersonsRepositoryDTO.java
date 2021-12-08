package com.juliano.apimysql.repository;

import com.juliano.apimysql.model.PersonsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepositoryDTO extends JpaRepository<PersonsDTO, Long> {
}