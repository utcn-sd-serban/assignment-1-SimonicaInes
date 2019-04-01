package ro.utcn.is.a1.repository.jdbc;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.is.a1.entity.QuestionTag;
import ro.utcn.is.a1.repository.QuestionTagRepository;

import java.util.*;

@RequiredArgsConstructor
public class JdbcQuestionTagRepository implements QuestionTagRepository {

    private final JdbcTemplate template;


    @Override
    public QuestionTag save(QuestionTag tag) {
        if(tag.getId() != 0){
            update(tag);
        }
        else{
            int id= insert(tag);
            tag.setId(id);
        }
        return tag;
    }

    @Override
    public Optional<QuestionTag> findById(int id) {
        List<QuestionTag> tags = template.query("SELECT * FROM questiontags WHERE id = ?", new QuestionTagMapper(), id);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    @Override
    public List<Integer> findByTagId(int id) {
        List<QuestionTag> tags = template.query("SELECT question_Id FROM questiontags WHERE id = ?", new QuestionTagMapper(), id);
        List<Integer> resList = new ArrayList<>();
        for(QuestionTag qt: tags){
            resList.add(qt.getQuestionId());
        }
        return resList;
    }

    @Override
    public List<Integer> findByQuestionId(int id) {
        List<QuestionTag> tags = template.query("SELECT tag_Id FROM questiontags WHERE id = ?", new QuestionTagMapper(), id);
        List<Integer> resList = new ArrayList<>();
        for(QuestionTag qt: tags){
            resList.add(qt.getTagId());
        }
        return resList;
    }

    @Override
    public void remove(QuestionTag tag) {
        template.update("DELETE FROM questiontags WHERE id = ?", tag.getId());
    }

    @Override
    public List<QuestionTag> findAll() {
        return template.query("SELECT * FROM questiontags", new QuestionTagMapper());
    }

    private int insert(QuestionTag tag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("questiontags");
        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("question_Id", tag.getQuestionId());
        data.put("tag_Id", tag.getTagId());

        return insert.executeAndReturnKey(data).intValue();


    }
    private void update(QuestionTag tag){
        template.update("UPDATE questiontags SET question_Id =?, tag_Id=?, WHERE id= ?",
                tag.getQuestionId(), tag.getTagId(), tag.getId());
    }
}
