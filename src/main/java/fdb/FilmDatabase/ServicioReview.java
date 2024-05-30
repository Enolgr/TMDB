package fdb.FilmDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioReview {
@Autowired
    private RepositorioReview repositorioReview;


    @Autowired
    private MongoTemplate mongoTemplate;
    public Review crearReview(String cuerpo, String imdbId){

        // se crea el objeto review.

        Review review = repositorioReview.insert(new Review(cuerpo,imdbId));

        // AÑADE RESEÑA A MONGO SI COINCIDE CON LA ID DE IMDB .
        // el .first hace que se añada solo en una pelicula.

        mongoTemplate.update(Pelicula.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }
    public List<Review> getReviewsByImdbId (String imdbId){
       return repositorioReview.findByImdbId(imdbId);
    }
}
