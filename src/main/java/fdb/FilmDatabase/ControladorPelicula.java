package fdb.FilmDatabase;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
// Pide un endpoint en api/peliculas.
@RequestMapping("/api/peliculas")


public class ControladorPelicula {

    // Autowired permite instanciar automáticamente por nosotros. No hace falta construir manualmente.
    @Autowired
    private ServicioPelicula servicioPelicula;

    //Entrega un mapping. Y un poco de magia porque lo entiendo regular.
    @GetMapping
    public ResponseEntity <List<Pelicula>> getAllPeliculas(){
        return new ResponseEntity<List<Pelicula>>(servicioPelicula.allPeliculas(),HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    // Pathvariable permite informar al fw que pasaremos como path el dato obtenido.
    public ResponseEntity<Pelicula> getUnaPelicula(@PathVariable String imdbId){
    return new ResponseEntity<Pelicula> (servicioPelicula.encontrarPorimdbID(imdbId),HttpStatus.OK);
    }

}
