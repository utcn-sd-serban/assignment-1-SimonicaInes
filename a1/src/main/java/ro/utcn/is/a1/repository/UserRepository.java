package ro.utcn.is.a1.repository;

import net.bytebuddy.dynamic.DynamicType;
import ro.utcn.is.a1.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    User save(User user);

    Optional<User> findById(int id);

    Optional<User> findByUsername(String username);

    void remove(User id);

    List<User> findAll();
}
