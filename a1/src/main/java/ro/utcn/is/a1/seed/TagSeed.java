package ro.utcn.is.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.is.a1.entity.Tag;
import ro.utcn.is.a1.repository.TagRepository;
import ro.utcn.is.a1.repository.TagRepositoryFactory;


@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TagSeed implements CommandLineRunner {
    private final TagRepositoryFactory factory;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        TagRepository repository = factory.createTagRepository();
        if(repository.findAll().isEmpty()){
            repository.save(new Tag( "kittens"));
            repository.save(new Tag( "memes"));
        }

    }
}
