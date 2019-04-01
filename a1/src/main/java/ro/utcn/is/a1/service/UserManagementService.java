package ro.utcn.is.a1.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.exception.UserNotFoundException;
import ro.utcn.is.a1.repository.UserRepository;
import ro.utcn.is.a1.repository.UserRepositoryFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagementService{
    private final UserRepositoryFactory repositoryFactory;


    @Transactional
    public List<User> listUsers() {
        return repositoryFactory.createUserRepository().findAll();
    }

    @Transactional
    public User addUser(String username, String password) {
        return repositoryFactory.createUserRepository().save(new User(username, password));
    }

    @Transactional
    public void updateUsername(int id, String username) {
        UserRepository repository = repositoryFactory.createUserRepository();
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setUsername(username);
        repository.save(user);
    }

    @Transactional
    public void removeUser(int id) {
        UserRepository repository = repositoryFactory.createUserRepository();
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.remove(user);
    }

    @Transactional
    public User getUser(String username){
          UserRepository repository = repositoryFactory.createUserRepository();
          User user= repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
          return user;
    }


}
