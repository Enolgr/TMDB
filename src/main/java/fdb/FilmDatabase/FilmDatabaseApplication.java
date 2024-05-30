package fdb.FilmDatabase;

//Contiene un método para ejecutar.
import org.springframework.boot.SpringApplication;
//Importa las anotaciones especiales.
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
@SpringBootApplication
public class FilmDatabaseApplication {

	//Llamando a SpringApplication funciona.
	public static void main(String[] args) {
		SpringApplication.run(FilmDatabaseApplication.class, args);
	}


}
