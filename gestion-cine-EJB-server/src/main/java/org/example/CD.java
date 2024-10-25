package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "cd")
@Setter
@Getter
public class CD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean isAvailable;

    @ManyToOne
    private Users borrowedBy;
}
