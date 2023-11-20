package com.webservices.graphql.service;

import com.webservices.graphql.model.Game;
import com.webservices.graphql.model.Games;
import com.webservices.graphql.model.Infos;
import com.webservices.graphql.repository.GameRepository;
import lombok.AllArgsConstructor;
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


    public Games findGames(Integer page, String genre, String platform, String studio) {
        Pageable pageable = PageRequest.of(page, 10); // Exemple avec 10 jeux par page.
        Page<Game> gamePage = gameRepository.findByGenresAndPlatformAndStudios(genre, platform, studio, pageable);
        return mapToGames(gamePage);
    }

    private Games mapToGames(Page<Game> gamePage) {
        Infos infos = new Infos(); // Supposons que vous avez un constructeur approprié ou des setters.
        infos.setPages(gamePage.getTotalPages());
        infos.setCount(gamePage.getNumber());
        infos.setNextPage(gamePage.hasNext());
        infos.setPreviousPage(gamePage.hasPrevious());

        Games games = new Games();
        games.setInfos(infos);
        games.setResults(gamePage.getContent());
        return games;
    }

    public Game findGameById(String id) throws ChangeSetPersister.NotFoundException {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
