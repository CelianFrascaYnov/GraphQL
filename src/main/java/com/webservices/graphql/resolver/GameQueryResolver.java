package com.webservices.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.webservices.graphql.model.Game;
import com.webservices.graphql.model.Games;
import com.webservices.graphql.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@RequiredArgsConstructor
public class GameQueryResolver implements GraphQLQueryResolver {

    private final GameService gameService;

    @QueryMapping
    public Games games(@Argument Integer page, @Argument String genre, @Argument String platform, @Argument String studio) {
        return gameService.findGames(page, genre, platform, studio);
    }

    @QueryMapping
    public Game game(@Argument String id) {
        return gameService.findGameById(id);
    }
}

