package backEnd.springApp.CRUDText.Controller;


import backEnd.springApp.CRUDText.Model.TextModel;
import backEnd.springApp.CRUDText.Repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class textController {
    @Autowired
    TextRepository contenuTextRepository;

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<List<TextModel>> getAllTutorials(@PathVariable("title") String title) {
       /* try {
            List<TextModel> texts = new ArrayList<TextModel>();

            if (title == null)
                contenuTextRepository.findAll().forEach(texts::add);
            else
                contenuTextRepository.findByTitleContaining(title).forEach(texts::add);

            if (texts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(texts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        List<TextModel> textModels =  this.contenuTextRepository.findByTitleContaining(title);
        return new ResponseEntity<>(textModels,HttpStatus.OK);
    }

    @GetMapping("/ContenuText/{id}")
    public ResponseEntity<TextModel> getTutorialById(@PathVariable("id") String id) {
        Optional<TextModel> contenuTextData = contenuTextRepository.findById(id);

        if (contenuTextData.isPresent()) {
            return new ResponseEntity<>(contenuTextData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ContenuText")
    public ResponseEntity<TextModel> createTutorial(@RequestBody TextModel contenuText) {
        try {
            TextModel _texts = contenuTextRepository.save(new TextModel(contenuText.getTitle(), contenuText.getDescription(), false));
            return new ResponseEntity<>(_texts, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ContenuText/{id}")
    public ResponseEntity<TextModel> updateTutorial(@PathVariable("id") String id, @RequestBody TextModel tutorial) {
        Optional<TextModel> textsData = contenuTextRepository.findById(id);

        if (textsData.isPresent()) {
            TextModel _texts = textsData.get();
            _texts.setTitle(tutorial.getTitle());
            _texts.setDescription(tutorial.getDescription());
            _texts.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(contenuTextRepository.save(_texts), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ContenuText/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        try {
            contenuTextRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/ContenuText")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            contenuTextRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ContenuText/published")
    public ResponseEntity<List<TextModel>> findByPublished() {
        try {
            List<TextModel> texts = contenuTextRepository.findByPublished(true);

            if (texts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(texts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
