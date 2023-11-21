package com.webservices.graphql.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.webservices.graphql.model.POJO.studio.CreateStudioInput;
import com.webservices.graphql.model.POJO.studio.UpdateStudioInput;
import com.webservices.graphql.model.Studio;
import com.webservices.graphql.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@RequiredArgsConstructor
public class StudioMutationResolver implements GraphQLMutationResolver {

    private final StudioService studioService;

    @MutationMapping
    public Studio createStudio(@Argument CreateStudioInput input) {
        return studioService.createStudio(input);
    }

    @MutationMapping
    public Studio updateStudio(@Argument String id, @Argument UpdateStudioInput input) {
        return studioService.updateStudio(id, input);
    }

    @MutationMapping
    public Boolean deleteStudio(@Argument String id) {
        return studioService.deleteStudio(id);
    }
}
