package com.webservices.graphql.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.webservices.graphql.model.Editor;
import com.webservices.graphql.model.POJO.editor.CreateEditorInput;
import com.webservices.graphql.model.POJO.editor.UpdateEditorInput;
import com.webservices.graphql.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@RequiredArgsConstructor
public class EditorMutationResolver implements GraphQLMutationResolver {

    private final EditorService editorService;

    @MutationMapping
    public Editor createEditor(@Argument CreateEditorInput input) {
        return editorService.createEditor(input);
    }

    @MutationMapping
    public Editor updateEditor(@Argument String id, @Argument UpdateEditorInput input) {
        return editorService.updateEditor(id, input);
    }

    @MutationMapping
    public Boolean deleteEditor(@Argument String id) {
        return editorService.deleteEditor(id);
    }

}
