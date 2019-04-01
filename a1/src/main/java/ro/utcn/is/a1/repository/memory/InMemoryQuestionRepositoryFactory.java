package ro.utcn.is.a1.repository.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.QuestionRepository;
import ro.utcn.is.a1.repository.QuestionRepositoryFactory;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryQuestionRepositoryFactory implements QuestionRepositoryFactory {
    private final InMemoryQuestionRepository repository = new InMemoryQuestionRepository();
    @Override
    public QuestionRepository createQuestionRepository() {
        return repository;
    }
}
