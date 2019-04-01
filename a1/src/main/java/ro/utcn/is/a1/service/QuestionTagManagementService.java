package ro.utcn.is.a1.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.is.a1.entity.QuestionTag;
import ro.utcn.is.a1.exception.QuestionTagNotFoundException;
import ro.utcn.is.a1.repository.QuestionTagRepository;
import ro.utcn.is.a1.repository.QuestionTagRepositoryFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionTagManagementService {
    private final QuestionTagRepositoryFactory repositoryFactory;
    @Transactional
    public List<QuestionTag> listQuestionTags() {
        return repositoryFactory.createQuestionTagRepository().findAll();
    }

    @Transactional
    public QuestionTag addQuestionTag(int questionId, int tagId) {
        return repositoryFactory.createQuestionTagRepository().save(new QuestionTag(questionId,tagId));
    }

    @Transactional
    public void updateQuestionTag(int id, int questionId, int tagId) {
        QuestionTagRepository repository = repositoryFactory.createQuestionTagRepository();
        QuestionTag qTag = repository.findById(id).orElseThrow(QuestionTagNotFoundException::new);
        qTag.setQuestionId(questionId);
        qTag.setTagId(tagId);
        repository.save(qTag);
    }

    @Transactional
    public void removeQuestionTag(int id) {
        QuestionTagRepository repository = repositoryFactory.createQuestionTagRepository();
        QuestionTag qTag = repository.findById(id).orElseThrow(QuestionTagNotFoundException::new);
        repository.remove(qTag);
    }

    @Transactional
    public List<Integer> getQuestionTagQuestionIds(int id){
        QuestionTagRepository repository = repositoryFactory.createQuestionTagRepository();
        List<QuestionTag> qTag= repository.findAll();
        List<Integer> resultList= new ArrayList<>();
        for(QuestionTag q : qTag){
            resultList.add(q.getQuestionId());
        }
        return resultList;
    }

    @Transactional
    public List<Integer> findByTagId(int t){
        return repositoryFactory.createQuestionTagRepository().findByTagId(t);
    }
    @Transactional
    public List<Integer> findByQuestionId(int t){
        return repositoryFactory.createQuestionTagRepository().findByQuestionId(t);
    }
}
