package com.parthibanrajasekaran.repository;

import com.parthibanrajasekaran.controller.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {

}
