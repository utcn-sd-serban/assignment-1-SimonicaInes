package ro.utcn.is.a1.repository.memory;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.UserRepository;
import ro.utcn.is.a1.repository.UserRepositoryFactory;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryUserRepositoryFactory implements UserRepositoryFactory {
    private final InMemoryUserRepository repository = new InMemoryUserRepository();
    @Override
    public UserRepository createUserRepository() {
        return repository;
    }
}