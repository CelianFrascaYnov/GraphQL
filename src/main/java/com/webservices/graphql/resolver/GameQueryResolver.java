package com.webservices.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.webservices.graphql.model.Game;
import com.webservices.graphql.model.Games;
import com.webservices.graphql.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameQueryResolver implements GraphQLQueryResolver {

    private final GameService gameService;

    public Games games(Integer page, String genre, String platform, String studio) {
        return gameService.findGames(page, genre, platform, studio);
    }

    public Game game(String id) throws ChangeSetPersister.NotFoundException {
        return gameService.findGameById(id);
    }
}

