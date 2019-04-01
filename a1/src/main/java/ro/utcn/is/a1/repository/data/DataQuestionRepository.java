package ro.utcn.is.a1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.is.a1.entity.Question;
import ro.utcn.is.a1.repository.QuestionRepository;

public interface DataQuestionRepository extends Repository<Question,Integer>, QuestionRepository {
    void delete(Question question);
    @Override
    default void remove(Question question){
        delete(question);
    }
}
