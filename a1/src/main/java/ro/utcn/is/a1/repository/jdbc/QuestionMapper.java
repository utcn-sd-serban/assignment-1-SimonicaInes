package ro.utcn.is.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.is.a1.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int i) throws SQLException {
        return new Question(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getInt("author_Id"),
                rs.getString("textq"),
                rs.getDate("dateq"));
    }
}
