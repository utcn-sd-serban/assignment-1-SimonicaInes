package ro.utcn.is.a1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.is.a1.entity.QuestionTag;
import ro.utcn.is.a1.repository.QuestionTagRepository;

public interface DataQuestionTagRepository extends Repository<QuestionTag, Integer>, QuestionTagRepository {
    void delete(QuestionTag qTag);
    @Override
    default void remove(QuestionTag qTag){
        delete(qTag);
    }
}
