package ro.utcn.is.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.QuestionTagRepository;
import ro.utcn.is.a1.repository.QuestionTagRepositoryFactory;

import java.util.Queue;
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JDBC")
public class JdbcQuestionTagRepositoryFactory implements QuestionTagRepositoryFactory {
    private final JdbcTemplate template;
    @Override
    public QuestionTagRepository createQuestionTagRepository() {
        return new JdbcQuestionTagRepository(template);
    }
}
