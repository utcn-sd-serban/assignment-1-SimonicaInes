package ro.utcn.is.a1.repository;

import ro.utcn.is.a1.entity.Question;

import java.util.List;
import java.util.Optional;
public interface QuestionRepository {

    Question save(Question question);

    Optional<Question> findById(int id);

    void remove(Question question);

    List<Question> findAll();
}
