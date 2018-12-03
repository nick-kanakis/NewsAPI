package com.nkanakis.newsAPI.repository;

import com.nkanakis.newsAPI.repository.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
