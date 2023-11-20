package com.webservices.graphql.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Editors {
    private Infos infos;
    private List<Editor> results;
}
