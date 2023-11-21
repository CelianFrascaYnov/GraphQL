package com.webservices.graphql.service;

import com.webservices.graphql.model.Editor;
import com.webservices.graphql.model.Editors;
import com.webservices.graphql.model.Infos;
import com.webservices.graphql.model.POJO.editor.CreateEditorInput;
import com.webservices.graphql.model.POJO.editor.UpdateEditorInput;
import com.webservices.graphql.repository.EditorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EditorService {

    private final EditorRepository editorRepository;

    public Editors findEditors(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Editor> editorPage = editorRepository.findAll(pageable);
        return mapToEditors(editorPage);
    }

    private Editors mapToEditors(Page<Editor> editorPage) {
        Infos infos = new Infos();
        infos.setPages(editorPage.getTotalPages());
        infos.setCount(editorPage.getNumber());
        infos.setNextPage(editorPage.hasNext());
        infos.setPreviousPage(editorPage.hasPrevious());

        Editors editors = new Editors();
        editors.setInfos(infos);
        editors.setResults(editorPage.getContent());
        return editors;
    }

    public Editor findEditorById(String id) throws ChangeSetPersister.NotFoundException {
        // Recherche d'un éditeur par ID.
        Optional<Editor> editor = editorRepository.findById(id);
        return editor.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public Editor createEditor(CreateEditorInput input) {
        Editor editor = new Editor();
        editor.setName(input.getName());
        return editorRepository.save(editor);
    }

    public Editor updateEditor(String id, UpdateEditorInput input) {
        Editor editor = editorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Editor not found"));
        editor.setName(input.getName());
        return editorRepository.save(editor);
    }

    public boolean deleteEditor(String id) {
        Editor editor = editorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Editor not found"));
        editorRepository.delete(editor);
        return true;
    }
}
