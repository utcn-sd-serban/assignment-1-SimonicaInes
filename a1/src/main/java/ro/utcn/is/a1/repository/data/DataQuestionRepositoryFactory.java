package ro.utcn.is.a1.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.QuestionRepository;
import ro.utcn.is.a1.repository.QuestionRepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name= "a1.repository-type", havingValue ="DATA")
public class DataQuestionRepositoryFactory implements QuestionRepositoryFactory {
    private final DataQuestionRepository questionRepository;
    @Override
    public QuestionRepository createQuestionRepository() {
        return questionRepository;
    }
}
