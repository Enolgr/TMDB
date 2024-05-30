package fdb.FilmDatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
// Esta anotación, proveniente de el import LOMBOK, hace que no haga falta ni poner Setters ni Getters, ni constructores.
@AllArgsConstructor // todos los argumentos privados en el constructor
@NoArgsConstructor // Ningún argumento en el constructor.

public class ReviewResponse {


    private List<Review> reviews;
    private Pelicula pelicula;
}
