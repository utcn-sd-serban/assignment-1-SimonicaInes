package ro.utcn.is.a1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.repository.UserRepository;

public interface DataUserRepository extends Repository<User, Integer>, UserRepository {
    void delete(User user);
    @Override
    default void remove(User user){
            delete(user);
        }
}
