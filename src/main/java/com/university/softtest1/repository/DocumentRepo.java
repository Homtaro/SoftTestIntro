package com.university.softtest1.repository;


import com.university.softtest1.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepo extends JpaRepository<DocumentEntity, Long> {

    Optional<DocumentEntity> findById(Long id);

    List<DocumentEntity> findByAccessLogin(String login);

    List<DocumentEntity> findBySignatureDateNotNullAndAccessLogin(String login);

    //I know I could just reverse previous query check, I'm lazy
    List<DocumentEntity> findBySignatureDateNullAndAccessLogin(String login);

    List<DocumentEntity> findByCreationDateBetween(Date date_start, Date date_end);


}
