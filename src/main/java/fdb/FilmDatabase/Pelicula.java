package fdb.FilmDatabase;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


// La anotación Document permite que esta clase se vea como un "Document" de mongoDB. En este caso de la colección movies.
@Document(collection="movies")
@Data // Esta anotación, proveniente de el import LOMBOK, hace que no haga falta ni poner Setters ni Getters, ni constructores.
@AllArgsConstructor // todos los argumentos privados en el constructor
@NoArgsConstructor // Ningún argumento en el constructor.

public class Pelicula {
    // La anotación ID actúa como "bloqueador" para que ID sea siempre única.
    @Id
    private ObjectId _id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private List<String> genres;
    private String poster;
    private List <String> backdrops;

    //Esto hara que las reviews estén almacenadas en una colección diferente.
    @DocumentReference
    private List <Review> reviewIds;

}
