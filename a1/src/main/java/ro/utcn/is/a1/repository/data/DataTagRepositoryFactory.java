package ro.utcn.is.a1.repository.data;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.TagRepository;
import ro.utcn.is.a1.repository.TagRepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name= "a1.repository-type", havingValue ="DATA")
public class DataTagRepositoryFactory implements TagRepositoryFactory {
    private final DataTagRepository tagRepository;
    @Override
    public TagRepository createTagRepository() {
        return tagRepository;
    }
}
