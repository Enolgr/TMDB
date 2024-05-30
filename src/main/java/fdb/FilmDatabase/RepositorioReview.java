package fdb.FilmDatabase;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// herencia del repositorio de mongo con los valores.
@Repository
public interface RepositorioReview extends MongoRepository <Review, ObjectId> {

    List<Review> findByImdbId (String imdbId);
}
