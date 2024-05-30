package fdb.FilmDatabase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ControladorHome {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }


    @GetMapping("/{imdbId}")
    public String home(Model model, @PathVariable String imdbId)  {
        model.addAttribute("imdbId",imdbId);
        return "review.html";
    }
}
