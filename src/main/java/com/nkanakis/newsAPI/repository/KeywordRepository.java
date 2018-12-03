package com.nkanakis.newsAPI.repository;

import com.nkanakis.newsAPI.repository.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
