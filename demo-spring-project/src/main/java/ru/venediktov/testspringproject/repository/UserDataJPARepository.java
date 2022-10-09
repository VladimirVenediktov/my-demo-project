package ru.venediktov.testspringproject.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.venediktov.testspringproject.model.User;

public interface UserDataJPARepository extends CrudRepository<User, String> {

  Optional<User> findUserByLogin(String login);

  Optional<User> findUserById(String id);

}
