package ro.utcn.is.a1.repository.memory;

import ro.utcn.is.a1.entity.QuestionTag;
import ro.utcn.is.a1.repository.QuestionTagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryQuestionTagRepository implements QuestionTagRepository {
    private final Map<Integer, QuestionTag> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public QuestionTag save(QuestionTag qTag) {
        if (qTag.getId() == 0) {
            qTag.setId(currentId.incrementAndGet());
        }
        data.put(qTag.getId(), qTag);
        return qTag;
    }

    @Override
    public Optional<QuestionTag> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Integer> findByTagId(int id) {
        List<Integer> l = new ArrayList<>();
        findAll().forEach(s->{if(s.getTagId() == id) l.add(s.getQuestionId());});
        return l;
    }

    @Override
    public List<Integer> findByQuestionId(int id) {
        List<Integer> l = new ArrayList<>();
        findAll().forEach(s->{if(s.getQuestionId() == id) l.add(s.getTagId());});
        return l;
    }

    @Override
    public void remove(QuestionTag tag) {
        data.remove(tag.getId());

    }

    @Override
    public List<QuestionTag> findAll() {
        return new ArrayList<>(data.values());
    }
}
