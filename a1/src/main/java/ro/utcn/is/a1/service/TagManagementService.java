package ro.utcn.is.a1.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.is.a1.entity.Question;
import ro.utcn.is.a1.entity.Tag;
import ro.utcn.is.a1.exception.QuestionNotFoundException;
import ro.utcn.is.a1.exception.TagNotFoundException;
import ro.utcn.is.a1.exception.UserNotFoundException;
import ro.utcn.is.a1.repository.QuestionRepository;
import ro.utcn.is.a1.repository.TagRepository;
import ro.utcn.is.a1.repository.TagRepositoryFactory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagManagementService {
    private final TagRepositoryFactory repositoryFactory;

    @Transactional
    public List<Tag> listTags() {
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional
    public Tag addTag(String tagText) {
        return repositoryFactory.createTagRepository().save(new Tag(tagText));
    }

    @Transactional
    public void updateTag(int id, String tagText) {
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findById(id).orElseThrow(TagNotFoundException::new);
        tag.setTagText(tagText);
        repository.save(tag);
    }

    @Transactional
    public void removeTag(int id) {
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findById(id).orElseThrow(TagNotFoundException::new);
        repository.remove(tag);
    }

    @Transactional
    public String getTagText(int id){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag= repository.findById(id).orElseThrow(TagNotFoundException::new);
        return tag.getTagText();
    }

    @Transactional
    public Tag getTag(String s){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag= repository.findByTagText(s).orElseThrow(TagNotFoundException::new);
        return tag;
    }
    @Transactional
    public Tag findById(int id){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findById(id).orElseThrow(TagNotFoundException::new);
        return tag;
    }
}
