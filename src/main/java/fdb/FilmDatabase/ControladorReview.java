package fdb.FilmDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/peliculas/reviews")

public class ControladorReview {

    @Autowired
    private ServicioReview servicioReview;

    @Autowired
    private ServicioPelicula servicioPelicula;

    @PostMapping
    public ResponseEntity<Review> crearReview(@RequestBody Map<String, String>payload){
        return new ResponseEntity<Review>(servicioReview.crearReview(payload.get("reviewBody"),payload.get("imdbId")), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    // Pathvariable permite informar al fw que pasaremos como path el dato obtenido.
    public ResponseEntity<ReviewResponse> getUnaPelicula(@PathVariable String imdbId){
        List<Review> reviews = servicioReview.getReviewsByImdbId(imdbId);
        Pelicula pelicula = servicioPelicula.encontrarPorimdbID(imdbId);

        ReviewResponse response = new ReviewResponse(reviews, pelicula);

        return new ResponseEntity<ReviewResponse>(response,HttpStatus.OK);
    }
}
