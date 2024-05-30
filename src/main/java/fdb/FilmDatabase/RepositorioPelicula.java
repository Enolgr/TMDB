package fdb.FilmDatabase;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// El extends MongoRepository permite heredar la interfaz default de Mongo.
// La anotacion repository permite al fw entenderlo.
@Repository
public interface RepositorioPelicula extends MongoRepository <Pelicula, ObjectId>{


    // Este metodo permite encontrar las peliculas por imdbid en vez de por id para ganar seguridad.
    Pelicula findByImdbId (String imdbId);
    // el nombre debe empezar por FINDBY!!
}

