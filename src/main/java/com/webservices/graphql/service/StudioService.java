package com.webservices.graphql.service;

import com.webservices.graphql.model.Infos;
import com.webservices.graphql.model.POJO.studio.CreateStudioInput;
import com.webservices.graphql.model.POJO.studio.UpdateStudioInput;
import com.webservices.graphql.model.Studio;
import com.webservices.graphql.model.Studios;
import jakarta.persistence.EntityNotFoundException;
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

    public Studio createStudio(CreateStudioInput input) {
        Studio studio = new Studio();
        studio.setName(input.getName());
        return studioRepository.save(studio);
    }

    public Studio updateStudio(String id, UpdateStudioInput input) {
        Studio studio = studioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Studio not found"));
        studio.setName(input.getName());
        return studioRepository.save(studio);
    }

    public boolean deleteStudio(String id) {
        Studio studio = studioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Studio not found"));
        studioRepository.delete(studio);
        return true;
    }
}
