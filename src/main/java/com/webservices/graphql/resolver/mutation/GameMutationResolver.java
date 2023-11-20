package com.webservices.graphql.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.webservices.graphql.model.Game;
import com.webservices.graphql.model.POJO.game.CreateGameInput;
import com.webservices.graphql.model.POJO.game.UpdateGameInput;
import com.webservices.graphql.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@RequiredArgsConstructor
public class GameMutationResolver implements GraphQLMutationResolver {
    private final GameService gameService;

    @MutationMapping
    public Game createGame(@Argument CreateGameInput input) {
        return gameService.createGame(input);
    }

    @MutationMapping
    public Game updateGame(@Argument String id, @Argument UpdateGameInput input) {
        return gameService.updateGame(id, input);
    }

    @MutationMapping
    public Boolean deleteGame(@Argument String id) {
        return gameService.deleteGame(id);
    }
}
