package com.webservices.graphql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private List<String> genres;

    private Integer publicationDate;

    @ManyToMany
    @JoinTable(
            name = "game_editor",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "editor_id")
    )
    private List<Editor> editors;

    @ManyToMany
    @JoinTable(
            name = "game_studio",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private List<Studio> studios;

    @ElementCollection
    private List<String> platform;
}