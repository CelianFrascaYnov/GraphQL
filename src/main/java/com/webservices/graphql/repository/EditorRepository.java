package com.webservices.graphql.repository;

import com.webservices.graphql.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<Editor, String> {
}
