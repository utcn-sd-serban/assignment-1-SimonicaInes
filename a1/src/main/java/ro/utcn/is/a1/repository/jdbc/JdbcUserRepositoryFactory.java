package ro.utcn.is.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.UserRepository;
import ro.utcn.is.a1.repository.UserRepositoryFactory;


@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JDBC")
public class JdbcUserRepositoryFactory implements UserRepositoryFactory {
    private final JdbcTemplate template;

    @Override
    public UserRepository createUserRepository() {
        return new JdbcUserRepository(template);
    }
}
