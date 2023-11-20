package com.webservices.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.webservices.graphql.model.Studio;
import com.webservices.graphql.model.Studios;
import com.webservices.graphql.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@RequiredArgsConstructor
public class StudioQueryResolver implements GraphQLQueryResolver {

    private final StudioService studioService;

    @QueryMapping
    public Studios studios(@Argument Integer page) {
        return studioService.findStudios(page);
    }

    @QueryMapping
    public Studio studio(@Argument String id) throws ChangeSetPersister.NotFoundException {
        return studioService.findStudioById(id);
    }
}
