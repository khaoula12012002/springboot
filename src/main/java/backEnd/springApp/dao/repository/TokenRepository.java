package backEnd.springApp.dao.repository;
import backEnd.springApp.dao.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token,Integer> {
    List<Token> findByUser_IdAndExpiredIsFalseOrRevokedIsFalse(String userId);

    Optional<Token> findByToken(String token);
}
