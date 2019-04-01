package ro.utcn.is.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.is.a1.entity.QuestionTag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionTagMapper implements RowMapper<QuestionTag> {

    @Override
    public QuestionTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionTag(rs.getInt("id"),
                rs.getInt("question_Id"),
                rs.getInt("tag_Id"));
    }
}
