package fdb.FilmDatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


// La anotación Document permite que esta clase se vea como un "Document" de mongoDB. En este caso de la colección reviews.
@Document(collection = "reviews")

@Data // Esta anotación, proveniente de el import LOMBOK, hace que no haga falta ni poner Setters ni Getters, ni constructores.
@AllArgsConstructor // todos los argumentos privados en el constructor
@NoArgsConstructor // Ningún argumento en el constructor.


public class Review {

    @Id
    private ObjectId id_;
    private String body;
    private String imdbId;

    // se genera un constructor solamente del cuerpo para no tener que utilizar ID en el caso de las reviews.
    public Review(String body, String imdbId) {
        this.body = body;
        this.imdbId = imdbId;
    }
}
