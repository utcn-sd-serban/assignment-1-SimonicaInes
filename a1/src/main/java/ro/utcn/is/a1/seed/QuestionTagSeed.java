package ro.utcn.is.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.is.a1.entity.QuestionTag;
import ro.utcn.is.a1.repository.QuestionTagRepositoryFactory;
import ro.utcn.is.a1.repository.QuestionTagRepository;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionTagSeed implements CommandLineRunner {
    private final QuestionTagRepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        QuestionTagRepository repository = factory.createQuestionTagRepository();
        if(repository.findAll().isEmpty()){
        repository.save(new QuestionTag(1,1));
        repository.save(new QuestionTag(1,2));
        repository.save(new QuestionTag(2,1));
        repository.save(new QuestionTag(3,2));
        }
    }
}
