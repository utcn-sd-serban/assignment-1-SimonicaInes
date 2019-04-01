package ro.utcn.is.a1.repository.jdbc;

import ro.utcn.is.a1.entity.Tag;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tag(rs.getInt("id"),
                rs.getString("tag_Text"));
    }
}
