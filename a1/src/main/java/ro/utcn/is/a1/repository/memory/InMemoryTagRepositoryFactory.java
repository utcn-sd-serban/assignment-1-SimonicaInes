package ro.utcn.is.a1.repository.memory;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.TagRepository;
import ro.utcn.is.a1.repository.TagRepositoryFactory;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")

public class InMemoryTagRepositoryFactory implements TagRepositoryFactory {
    private final InMemoryTagRepository repository = new InMemoryTagRepository();
    @Override
    public TagRepository createTagRepository() {
        return repository;
    }
}
