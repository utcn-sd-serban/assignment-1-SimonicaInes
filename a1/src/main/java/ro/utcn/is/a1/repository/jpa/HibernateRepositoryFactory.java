package ro.utcn.is.a1.repository.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.UserRepository;
import ro.utcn.is.a1.repository.UserRepositoryFactory;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JPA")
public class HibernateRepositoryFactory implements UserRepositoryFactory {
    private final EntityManager entityManager;

    @Override
    public UserRepository createUserRepository() {
        return new HibernateUserRepository(entityManager);
    }
}
