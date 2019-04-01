package ro.utcn.is.a1.repository.data;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.QuestionTagRepository;
import ro.utcn.is.a1.repository.QuestionTagRepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name= "a1.repository-type", havingValue ="DATA")
public class DataQuestionTagRepositoryFactory implements QuestionTagRepositoryFactory {
    private final DataQuestionTagRepository questionTagRepository;
    @Override
    public QuestionTagRepository createQuestionTagRepository() {
        return questionTagRepository;
    }
}
