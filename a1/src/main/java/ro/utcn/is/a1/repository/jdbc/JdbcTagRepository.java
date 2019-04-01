package ro.utcn.is.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.is.a1.entity.Tag;
import ro.utcn.is.a1.repository.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {

    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) {

        if (tag.getId() == null) {
            tag.setId(insert(tag));
        } else {
            update(tag);
        }
        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> tags = template.query("SELECT * FROM tag WHERE id = ?", new TagMapper(), id);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    @Override
    public Optional<Tag> findByTagText(String tagText) {
        List<Tag> tags = template.query("SELECT * FROM tag WHERE tag_Text = ?", new TagMapper(), tagText);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    @Override
    public void remove(Tag tag) {
        template.update("DELETE FROM tag WHERE id = ?", tag.getId());
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM tag", new TagMapper());
    }

    private int insert(Tag tag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("tag_Text", tag.getTagText());

        return insert.executeAndReturnKey(data).intValue();


    }
    private void update(Tag tag){
        template.update("UPDATE tag SET tag_Text =?, WHERE id= ?",
                tag.getTagText(), tag.getId());
    }
}
