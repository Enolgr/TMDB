package fdb.FilmDatabase;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// En esta clase se ejecutan los metodos de acceso a la BBDD.

@Service
public class ServicioPelicula {

    // Autowired permite instanciar automáticamente por nosotros. No hace falta construir manualmente.
    @Autowired
    private RepositorioPelicula repositorioPelicula;
    public List<Pelicula> allPeliculas() {
        return repositorioPelicula.findAll();
        // El metodo findAll es un método del repositiorio de MONGODB
    }
    public  Pelicula encontrarPorimdbID(String imdbId) {
            return repositorioPelicula.findByImdbId(imdbId);
        }

}
