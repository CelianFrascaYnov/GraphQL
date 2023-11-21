package com.webservices.graphql.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Infos {
    private int count;
    private int pages;
    private Boolean nextPage; // Peut être null si c'est la dernière page
    private Boolean previousPage; // Peut être null si c'est la première page
}
