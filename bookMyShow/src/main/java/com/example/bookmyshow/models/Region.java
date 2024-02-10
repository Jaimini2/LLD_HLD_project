package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Region  extends BaseModel{
    String name;
    @OneToMany
    List<Theater> theaterList;

//    @ManyToMany
//    List<Movie> movies;
}
