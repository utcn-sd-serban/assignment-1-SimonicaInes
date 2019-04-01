package ro.utcn.is.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.is.a1.entity.Question;
import ro.utcn.is.a1.repository.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate template;

    @Override
    public Question save(Question question) {
        if(question.getId() != 0){
            update(question);
        }
        else{
            int id= insert(question);
            question.setId(id);
        }
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {

        List<Question> questions= template.query("SELECT * FROM question WHERE id= ?",
                (resultSet,i) -> new Question(resultSet.getInt("id"),
                        resultSet.getString("title"), resultSet.getInt("author_Id"),
                        resultSet.getString("textq"),resultSet.getDate("dateq")));
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));

    }

    @Override
    public void remove(Question question) {
        template.update("DELETE FROM question WHERE id = ?", question.getId());
    }

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM question", (resultSet,i) ->
                new Question(resultSet.getInt("id"),
                        resultSet.getString("title"), resultSet.getInt("author_Id"),
                        resultSet.getString("textq"),resultSet.getDate("dateq")));

    }

    private int insert(Question question){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question");
        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("title", question.getTitle());
        data.put("author_Id", question.getAuthorId());
        data.put("textq", question.getText());
        return insert.executeAndReturnKey(data).intValue();


    }
    private void update(Question question){
        template.update("UPDATE question SET title =?, textq =?, WHERE id= ?",
                question.getTitle(), question.getTitle(), question.getId());
    }
}
