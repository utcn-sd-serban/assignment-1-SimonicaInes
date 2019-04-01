package ro.utcn.is.a1.repository;

import ro.utcn.is.a1.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Tag save(Tag tag);

    Optional<Tag> findById(int id);

    Optional<Tag> findByTagText (String tagText);

    void remove(Tag tag);

    List<Tag> findAll();
}
