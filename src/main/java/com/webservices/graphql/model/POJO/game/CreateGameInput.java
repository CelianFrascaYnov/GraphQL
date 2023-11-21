package com.webservices.graphql.model.POJO.game;

import lombok.Data;
import java.util.List;

@Data
public class CreateGameInput {
    private String name;
    private List<String> genres;
    private Integer publicationDate;
    private List<String> platform;
    private List<String> editorIds;
    private List<String> studioIds;
}
