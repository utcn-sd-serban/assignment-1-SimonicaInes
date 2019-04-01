package ro.utcn.is.a1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.is.a1.entity.Tag;
import ro.utcn.is.a1.repository.TagRepository;

public interface DataTagRepository extends Repository<Tag, Integer>, TagRepository {
    void delete(Tag tag);
    @Override
    default void remove(Tag tag){
        delete(tag);
    }
}
