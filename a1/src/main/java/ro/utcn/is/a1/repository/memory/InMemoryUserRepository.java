package ro.utcn.is.a1.repository.memory;

import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    // we want to be thread-safe, because this class will be a singleton (just one instance)
    // in order to not use synchronized methods, we use thread-safe classes (ConcurrentHashMap and AtomicInteger)

    private final Map<Integer, User> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);


    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(currentId.incrementAndGet());
        }
        data.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {

        List<User> user = findAll();

        for(User u: user){
            if(u.getUsername().equals(username)){
                return Optional.ofNullable(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(User user) {

            data.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }
}
