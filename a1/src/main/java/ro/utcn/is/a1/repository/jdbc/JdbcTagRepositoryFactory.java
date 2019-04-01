package ro.utcn.is.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.TagRepository;
import ro.utcn.is.a1.repository.TagRepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JDBC")
public class JdbcTagRepositoryFactory implements TagRepositoryFactory {
    private final JdbcTemplate template;
    @Override
    public TagRepository createTagRepository() {
        return new JdbcTagRepository(template);
    }
}
