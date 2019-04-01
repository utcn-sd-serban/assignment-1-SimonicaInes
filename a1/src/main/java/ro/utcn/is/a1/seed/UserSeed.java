package ro.utcn.is.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.repository.UserRepository;
import ro.utcn.is.a1.repository.UserRepositoryFactory;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {
    private final UserRepositoryFactory factory;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UserRepository repository= factory.createUserRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new User( "ines", "111"));
            repository.save(new User( "admin", "admin"));
        }
    }


}
