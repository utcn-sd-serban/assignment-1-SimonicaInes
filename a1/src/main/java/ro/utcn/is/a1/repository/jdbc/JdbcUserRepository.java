package ro.utcn.is.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate template;

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM users", new UserMapper());
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(insert(user));
        } else {
            update(user);
        }
        return user;
    }

    @Override
    public void remove(User user) {
        template.update("DELETE FROM users WHERE id = ?", user.getId());
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> users = template.query("SELECT * FROM users WHERE id = ?", new UserMapper(), id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = template.query("SELECT * FROM users WHERE username = ?", new UserMapper(), username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    private int insert(User student) {
        // we use the SimpleJdbcInsert to easily retrieve the generated ID
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("users");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("username", student.getUsername());
        map.put("password", student.getPassword());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(User student) {
        template.update("UPDATE users SET username = ?, password = ? WHERE id = ?",
                student.getUsername(), student.getPassword(), student.getId());
    }
}
