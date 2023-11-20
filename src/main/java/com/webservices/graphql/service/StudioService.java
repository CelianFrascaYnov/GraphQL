package com.webservices.graphql.service;

import com.webservices.graphql.model.Infos;
import com.webservices.graphql.model.Studio;
import com.webservices.graphql.model.Studios;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.webservices.graphql.repository.StudioRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;

    public Studios findStudios(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Studio> studioPage = studioRepository.findAll(pageable);
        return mapToStudios(studioPage);
    }

    private Studios mapToStudios(Page<Studio> studioPage) {
        Infos infos = new Infos();
        infos.setPages(studioPage.getTotalPages());
        infos.setCount(studioPage.getNumber());
        infos.setNextPage(studioPage.hasNext());
        infos.setPreviousPage(studioPage.hasPrevious());

        Studios studios = new Studios();
        studios.setInfos(infos);
        studios.setResults(studioPage.getContent());
        return studios;
    }

    public Studio findStudioById(String id) throws ChangeSetPersister.NotFoundException {
        // Recherche d'un studio par ID.
        Optional<Studio> studio = studioRepository.findById(id);
        return studio.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
