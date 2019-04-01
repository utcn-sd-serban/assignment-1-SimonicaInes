package ro.utcn.is.a1.repository.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.QuestionTagRepository;
import ro.utcn.is.a1.repository.QuestionTagRepositoryFactory;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryQuestionTagRepositoryFactory implements QuestionTagRepositoryFactory {
    private final InMemoryQuestionTagRepository repository = new InMemoryQuestionTagRepository();

    @Override
    public QuestionTagRepository createQuestionTagRepository() {
        return repository;
    }
}
