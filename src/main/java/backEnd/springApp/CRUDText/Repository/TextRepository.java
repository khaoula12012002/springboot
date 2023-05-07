package backEnd.springApp.CRUDText.Repository;


import backEnd.springApp.CRUDText.Model.TextModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TextRepository extends MongoRepository<TextModel, String> {
    List<TextModel> findByPublished(boolean published);
    List<TextModel> findByTitleContaining(String title);
}
