package ro.utcn.is.a1.repository;

import ro.utcn.is.a1.entity.QuestionTag;

import java.util.List;
import java.util.Optional;

public interface QuestionTagRepository {
    QuestionTag save(QuestionTag tag);

    Optional<QuestionTag> findById(int id);

    List<Integer> findByTagId(int id);
    List<Integer> findByQuestionId(int id);

    void remove(QuestionTag tag);

    List<QuestionTag> findAll();
}
