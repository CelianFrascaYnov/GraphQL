package com.webservices.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.webservices.graphql.model.Studio;
import com.webservices.graphql.model.Studios;
import com.webservices.graphql.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudioQueryResolver implements GraphQLQueryResolver {

    private final StudioService studioService;

    public Studios studios(Integer page) {
        return studioService.findStudios(page);
    }

    public Studio studio(String id) throws ChangeSetPersister.NotFoundException {
        return studioService.findStudioById(id);
    }
}
