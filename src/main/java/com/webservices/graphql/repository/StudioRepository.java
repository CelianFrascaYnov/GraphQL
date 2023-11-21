package com.webservices.graphql.repository;

import com.webservices.graphql.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, String> {
}
