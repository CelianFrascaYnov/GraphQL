package com.webservices.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.webservices.graphql.model.Editor;
import com.webservices.graphql.model.Editors;
import com.webservices.graphql.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@RequiredArgsConstructor
public class EditorQueryResolver implements GraphQLQueryResolver {

    private final EditorService editorService;

    @QueryMapping
    public Editors editors(@Argument Integer page) {
        return editorService.findEditors(page);
    }

    @QueryMapping
    public Editor editor(@Argument String id) throws ChangeSetPersister.NotFoundException {
        return editorService.findEditorById(id);
    }
}
