package models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Region  extends BaseModel{
    String name;
    List<Theater> theaterList;

    List<Movie> movies;
}
