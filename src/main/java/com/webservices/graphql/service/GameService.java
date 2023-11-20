package com.webservices.graphql.service;

import com.webservices.graphql.model.Game;
import com.webservices.graphql.model.Games;
import com.webservices.graphql.model.Infos;
import com.webservices.graphql.model.POJO.game.CreateGameInput;
import com.webservices.graphql.model.POJO.game.UpdateGameInput;
import com.webservices.graphql.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
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

    public Game findGameById(String id)  {
        return gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));
    }

    public Game createGame(CreateGameInput input) {
        log.info("Creating a new game with name: {}", input.getName());
        try {
            Game game = new Game();
            game.setName(input.getName());
            game.setGenres(input.getGenres());
            game.setPublicationDate(input.getPublicationDate());
            game.setPlatform(input.getPlatform());
            // Assurez-vous que toutes les données nécessaires sont fournies
            if (game.getName() == null || game.getGenres() == null || game.getPublicationDate() == null || game.getPlatform() == null) {
                throw new IllegalArgumentException("Toutes les données requises ne sont pas fournies.");
            }

            // Enregistrez le jeu dans le référentiel
            return gameRepository.save(game);
        } catch (IllegalArgumentException e) {
            // Gérez l'exception d'argument illégal ici, par exemple, en la lançant à nouveau ou en effectuant un journal.
            throw e;
        } catch (Exception e) {
            // Gérez d'autres exceptions qui pourraient survenir lors de la création du jeu ici.
            // Vous pouvez également les logger pour le suivi des erreurs.
            throw new RuntimeException("Une erreur s'est produite lors de la création du jeu.", e);
        }
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
