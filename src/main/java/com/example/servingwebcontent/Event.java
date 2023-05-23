package com.example.servingwebcontent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @GeneratedValue
    @Id
    private int id;

    private String title;

    private String city;
}
