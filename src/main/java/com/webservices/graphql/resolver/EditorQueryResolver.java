package com.webservices.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.webservices.graphql.model.Editor;
import com.webservices.graphql.model.Editors;
import com.webservices.graphql.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditorQueryResolver implements GraphQLQueryResolver {

    private final EditorService editorService;

    public Editors editors(Integer page) {
        return editorService.findEditors(page);
    }

    public Editor editor(String id) throws ChangeSetPersister.NotFoundException {
        return editorService.findEditorById(id);
    }
}
