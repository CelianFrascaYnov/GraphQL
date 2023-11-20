package com.webservices.graphql.model.POJO.game;

import lombok.Data;
import java.util.List;

@Data
public class UpdateGameInput {
    private String name;
    private List<String> genres;
    private Integer publicationDate;
    private List<String> platform;
}
