package com.webservices.graphql.service;

import com.webservices.graphql.model.*;
import com.webservices.graphql.model.POJO.game.CreateGameInput;
import com.webservices.graphql.model.POJO.game.UpdateGameInput;
import com.webservices.graphql.repository.EditorRepository;
import com.webservices.graphql.repository.GameRepository;
import com.webservices.graphql.repository.StudioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final EditorRepository editorRepository;
    private final StudioRepository studioRepository;
    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    public Games findGames(Integer page, String genre, String platform, String studio) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Game> gamePage = gameRepository.findByGenresAndPlatformAndStudios(genre, platform, studio, pageable);
        return mapToGames(gamePage);
    }

    private Games mapToGames(Page<Game> gamePage) {
        Infos infos = new Infos();
        infos.setPages(gamePage.getTotalPages());
        infos.setCount(gamePage.getNumber());
        infos.setNextPage(gamePage.hasNext());
        infos.setPreviousPage(gamePage.hasPrevious());

        Games games = new Games();
        games.setInfos(infos);
        games.setResults(gamePage.getContent());
        return games;
    }

    public Game findGameById(String id) {
        return gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));
    }

    @Transactional
    public Game createGame(CreateGameInput input) {
        Game game = new Game();
        game.setName(input.getName());
        game.setGenres(input.getGenres());
        game.setPublicationDate(input.getPublicationDate());
        game.setPlatform(input.getPlatform());

        if (input.getEditorIds() != null) {
            List<Editor> editors = editorRepository.findAllById(input.getEditorIds());
            game.setEditors(editors);
        }

        if (input.getStudioIds() != null) {
            List<Studio> studios = studioRepository.findAllById(input.getStudioIds());
            game.setStudios(studios);
        }
        return gameRepository.save(game);
    }

    public Game updateGame(String id, UpdateGameInput input) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));
        if (input.getName() != null) game.setName(input.getName());
        if (input.getGenres() != null) game.setGenres(input.getGenres());
        if (input.getPublicationDate() != null) game.setPublicationDate(input.getPublicationDate());
        if (input.getPlatform() != null) game.setPlatform(input.getPlatform());
        return gameRepository.save(game);
    }

    public boolean deleteGame(String id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));
        gameRepository.delete(game);
        return true;
    }
}
