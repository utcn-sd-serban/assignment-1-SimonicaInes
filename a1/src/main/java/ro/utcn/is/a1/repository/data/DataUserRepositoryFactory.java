package ro.utcn.is.a1.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.repository.UserRepository;
import ro.utcn.is.a1.repository.UserRepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name= "a1.repository-type", havingValue ="DATA")
public class DataUserRepositoryFactory implements UserRepositoryFactory {
    private final DataUserRepository userRepository;
    @Override
    public UserRepository createUserRepository() {
        return userRepository;
    }
}
